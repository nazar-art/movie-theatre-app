package net.lelyak.edu.service;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private DiscountService discountService;

    public void saveTicket(Ticket ticket) {
        ticketRepository.put(ticket);
    }

    public Ticket bookTicket(Event event, Date date, String sits, User user) {
        double price = getTicketPrice(event, date, user);
        Ticket ticket = new Ticket();
        ticket.setName(sits);
        ticket.setEvent(event);
        ticket.setOnDate(date);
        ticket.setPrice(price);
        ticket.setUser(user);
        ticketRepository.put(ticket);
        return ticket;
    }

    public List<Ticket> getTicketsForUser(User user) {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .filter(e -> e != null)
                .filter(t -> Objects.equals(t.getUser().getId(), user.getId()))
                .collect(Collectors.toList());
        if (tickets == null) {
            tickets = Collections.<Ticket>emptyList();
        }
        return tickets;
    }

    public Double getTicketPrice(Event event, Date date, User user) {
        Double finalPrice = event.getTicketPrice();

        double discount = discountService.getDiscount(user, event, date);
        if (discount != 0) {
            finalPrice = finalPrice * (1 - discount);
        }
        return finalPrice;
    }

    public List<Ticket> getTicketsForEvent(Event event) {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .filter(e -> e != null)
                .filter(t -> t.getEvent().equals(event))
                .collect(Collectors.toList());
        if (tickets == null) {
            tickets = Collections.<Ticket>emptyList();
        }
        return tickets;
    }

    public List<Ticket> getTicketsForEventDate(Event event, Date date) {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .filter(t -> t.getEvent().equals(event)
                        && t.getOnDate().equals(date))
                .collect(Collectors.toList());

        if (tickets == null) {
            tickets = Collections.<Ticket>emptyList();
        }
        return tickets;
    }
}
