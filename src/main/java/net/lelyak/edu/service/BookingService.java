package net.lelyak.edu.service;

import net.lelyak.edu.dao.mock.DatabaseMock;
import net.lelyak.edu.entity.*;
import net.lelyak.edu.utils.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * BookingService - Manages tickets, prices, bookings
 *
 * getTicketPrice(event, date, time, seats, user) - returns price for ticket for specified event on specific date and time for specified seats.
 * User is needed to calculate discount (see below)
 * + Event is needed to get base price, rating
 * + Vip seats should cost more than regular seats (For example, 2xBasePrice)
 * + All prices for high rated movies should be higher (For example, 1.2xBasePrice)
 *
 * bookTicket(user, ticket) - user could  be registered or not.
 * If user is registered, then booking information is stored for that user.
 * Purchased tickets for particular event should be stored
 *
 * getTicketsForEvent(event, date) - get all purchased tickets for event for specific date
 */
@Service
public class BookingService {

    public BookingService() {
    }

    public Double getTicketPrice(Event event, Calendar date, Calendar time,
                               SeatType seatType, User user) {
        Double basePrice = event.getPrice();
        EventRating rating = event.getEventRating();

        basePrice = priceBySeatType(seatType, basePrice);
        priceByMovieRating(basePrice, rating);
        // todo calculate discount for user ???

        return basePrice;
    }

    public void bookTicket(User user, Ticket ticket) {
        Set<Ticket> tickets = user.getBookedTickets();
        if (!tickets.contains(ticket)) {
            user.addTicket(ticket);
        } else {
            Logger.warn("Ticket: %s is booked by user: %s", ticket, user);
        }
    }

    public List<Ticket> getTicketsForEvent(Event event, Calendar date) {
        List<Ticket> result = Collections.emptyList();
        Collection<Ticket> tickets = DatabaseMock.getTickets().values();

        result.addAll(tickets.stream()
                .filter(ticket -> ticket.getEvent() != null
                        && ticket.getEvent().getEventDateTime().contains(date))
                .collect(Collectors.toList()));
        return result;
    }

    private Double priceByMovieRating(Double price, EventRating rating) {
        switch (rating) {
            case HIGH:
                price = price * 1.2;
                break;
        }
        return price;
    }

    private Double priceBySeatType(SeatType seatType, Double price) {
        switch (seatType) {
            case VIP:
                price = price * 2;
                break;
        }
        return price;
    }
}
