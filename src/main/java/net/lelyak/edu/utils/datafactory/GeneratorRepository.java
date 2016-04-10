package net.lelyak.edu.utils.datafactory;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.entity.enums.UserRole;
import net.lelyak.edu.utils.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public final class GeneratorRepository {

    private RandomDataSource randomDataSource = new RandomDataSource();

    public User getRandomUser() {
        User user = new User();
        randomDataSource.fillEntity(user);
        Logger.debug("GeneratorRepository.getRandomUser: " + user.toString());
        return user;
    }

    public User getRandomAdminUser() {
        User user = new User();
        randomDataSource.fillEntity(user);
        user.setRole(UserRole.BOOKING_MANAGER.toString());
        Logger.debug("GeneratorRepository.getRandomUser: " + user.toString());
        return user;
    }

    public List<User> getRandomUserList(int count) {
        List<User> testUsers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            testUsers.add(getRandomUser());
        }
        return testUsers;
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

    public Ticket assignRandomTicketToUser(User user) {
        Ticket ticket = new Ticket();
        randomDataSource.fillEntity(ticket);
        ticket.setEvent(getRandomEvent());
        ticket.setUser(user);
        Logger.debug("GeneratorRepository.assignRandomTicketToUser: " + ticket);
        return ticket;
    }

    public Ticket assignRandomTicketToEvent(Event event) {
        Ticket ticket = new Ticket();
        randomDataSource.fillEntity(ticket);
        ticket.setEvent(event);
        ticket.setUser(getRandomUser());
        Logger.debug("GeneratorRepository.assignRandomTicketToEvent: " + ticket);
        return ticket;
    }

    public Ticket assignRandomTicketToUserAndEvent(User user, Event event) {
        Ticket ticket = new Ticket();
        randomDataSource.fillEntity(ticket);
        ticket.setEvent(event);
        ticket.setUser(user);
        Logger.debug("GeneratorRepository.assignRandomTicketToUserAndEvent: " + ticket);
        return ticket;
    }




}
