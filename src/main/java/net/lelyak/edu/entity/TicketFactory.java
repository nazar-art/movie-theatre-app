package net.lelyak.edu.entity;

import org.apache.commons.lang.math.RandomUtils;

public class TicketFactory {

    public static Ticket getTicket(Event event, Double price, User user) {
        Ticket ticket = new Ticket();
        ticket.setId(RandomUtils.nextInt(100));
        ticket.setPrice(price);
        ticket.setEvent(event);
        ticket.setUser(user);
        return ticket;
    }
}
