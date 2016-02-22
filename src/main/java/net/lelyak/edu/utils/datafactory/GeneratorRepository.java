package net.lelyak.edu.utils.datafactory;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.Logger;

public final class GeneratorRepository {

    private RandomDataSource randomDataSource = new RandomDataSource();

    public User getRandomUser() {
        User user = new User();
        randomDataSource.fillEntity(user);
        Logger.debug("GeneratorRepository.getRandomUser: " + user);
        return user;
    }

    public Event getRandomEvent() {
        Event event = new Event();
        randomDataSource.fillEntity(event);
        Logger.debug("GeneratorRepository.getRandomEvent: " + event);
        return event;
    }

    public Ticket getRandomTicket() {
        Ticket ticket = new Ticket();
        randomDataSource.fillEntity(ticket);
        Logger.debug("GeneratorRepository.getRandomTicket: " + ticket);
        return ticket;
    }

    public Ticket getRandomTicketWithEvent() {
        Ticket ticket = new Ticket();
        randomDataSource.fillEntity(ticket);
        ticket.setEvent(getRandomEvent());
        Logger.debug("GeneratorRepository.getRandomTicketWithEvent: " + ticket);
        return ticket;
    }

    public Ticket getRandomTicketWithUserAndEvent() {
        Ticket ticket = new Ticket();
        randomDataSource.fillEntity(ticket);
        ticket.setEvent(getRandomEvent());
        ticket.setUser(getRandomUser());
        Logger.debug("GeneratorRepository.getRandomTicketWithUserAndEvent: " + ticket);
        return ticket;
    }
}
