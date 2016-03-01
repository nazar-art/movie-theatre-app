package net.lelyak.edu.service;

import net.lelyak.edu.dao.impl.TicketDAO;
import net.lelyak.edu.entity.*;
import net.lelyak.edu.utils.Logger;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * BookingService - Manages tickets, prices, bookings
 *
 * getTicketPrice(event, dateTime, time, seats, user) - returns price for ticket for specified event on specific dateTime and time for specified seats.
 * User is needed to calculate discount (see below)
 * + Event is needed to get base price, rating
 * + Vip seats should cost more than regular seats (For example, 2xBasePrice)
 * + All prices for high rated movies should be higher (For example, 1.2xBasePrice)
 *
 * bookTicket(user, ticket) - user could  be registered or not.
 * If user is registered, then booking information is stored for that user.
 * Purchased tickets for particular event should be stored
 *
 * getTicketsForEvent(event, dateTime) - get all purchased tickets for event for specific dateTime
 */
@Service
public class BookingService {

    @Autowired
    @Qualifier(value = "discountService")
    private DiscountService discountService;

    @Autowired
    private TicketDAO ticketDao;
    @Autowired
    private TicketService ticketService;

    public BookingService() {
    }

    public Double getTicketPrice(Event event, Date dateTime, SeatType seatType, User user) {
        Double basePrice = event.getPrice();
        Rating rating = event.getRating();

        basePrice = priceBySeatType(seatType, basePrice);
        basePrice = priceByMovieRating(basePrice, rating);

        double discount = discountService.getDiscount(user, event, dateTime);
        basePrice *= basePrice * discount;

        return basePrice;
    }

    public void bookTicket(User user, Ticket ticket) {
        if (ticket.getUser() == null) {
            user.addTicket(ticket);
            ticketDao.save(ticket);
        } else {
            Logger.warn("Ticket: %s has already booked by another user: %s", ticket, ticket.getUser());
        }
    }

    public List<Ticket> getTicketsForEvent(Event event, Date date) {
        List<Ticket> result = Collections.emptyList();
//        Collection<Ticket> tickets = DatabaseMock.getTickets().values();
        Collection<Ticket> tickets = ticketDao.getAll();

        result.addAll(tickets.stream()
                .filter(ticket -> ticket.getEvent() != null
                        && ticket.getEvent().getAirDate().equals(date))
                .collect(toList()));
        return result;
    }

    private Double priceByMovieRating(Double price, Rating rating) {
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

    public List<Ticket> getTicketsForUser(User user) {
        Logger.info("BookingService.getTicketsForUser");
//        Collection<Ticket> allTickets = DatabaseMock.getTickets().values();
        /*return ticketDao.getAll().stream()
                .filter(e -> e.getUser() != null)
                .filter(e -> Objects.equals(e.getUser().getId(), user.getId()))
                .collect(toList());*/
        return ticketService.getTicketsForUser(user);
    }

    public boolean checkLucky(User user) {
        boolean result = RandomUtils.nextBoolean();
        String userName = user.getName().toUpperCase();

        if (result) {
            Logger.info(String.format("This user: %s is very lucky today !!!", userName));
        } else {
            Logger.info(String.format("This user: %s you will win next time!", userName));
        }
        return result;
    }
}
