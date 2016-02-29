package net.lelyak.edu.entity;

public class TicketFactory {

    public static Ticket getTicket(Event event, Double price, User user) {
        Ticket ticket = new Ticket();
//        ticket.setId(RandomUtils.nextLong());
        ticket.setPrice(price);
        ticket.setEvent(event);
        ticket.setUser(user);
        return ticket;
    }
}
