package net.lelyak.edu.service;

import net.lelyak.edu.dao.impl.TicketDaoImpl;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.TicketFactory;
import net.lelyak.edu.entity.User;
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
    private TicketDaoImpl ticketDao;

    @Autowired
    private DiscountService discountService;

    public Double getTicketPrice(Event event, Date date, User user) {
        Double finalPrice = event.getPrice();

        double discount = discountService.getDiscount(user, event, date);
        if (discount != 0) {
            finalPrice = finalPrice * (1 - discount);
        }
        return finalPrice;
    }

    public Ticket bookTicket(Event event, Date date, User user) {
        Double price = getTicketPrice(event, date, user);
        Ticket ticket = TicketFactory.getTicket(event, price, user);
        ticket.setUser(user);
        ticketDao.save(ticket);
        return ticket;
    }

    public List<Ticket> getTicketsForUser(User user) {
        List<Ticket> tickets = ticketDao.getAll().stream()
                .filter(e -> e != null)
                .filter(t -> Objects.equals(t.getUser().getId(), user.getId()))
                .collect(Collectors.toList());
        if (tickets == null) {
            tickets = Collections.<Ticket>emptyList();
        }
        return tickets;
    }
}
