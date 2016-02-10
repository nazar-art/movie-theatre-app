package net.lelyak.edu.dao.mock;

import net.lelyak.edu.entity.Auditorium;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;

import java.util.HashMap;
import java.util.Map;

public class DatabaseMock {

    private static Map<Integer, User> users = new HashMap<>();
    private static Map<Integer, Event> events = new HashMap<>();
    private static Map<Integer, Auditorium> auditoriums = new HashMap<>();
    private static Map<Integer, Ticket> tickets = new HashMap<Integer, Ticket>() {
        {
            put(1, new Ticket(1, events.get(0), 60d, users.get(0)));
            put(2, new Ticket(2, events.get(1), 70d, users.get(1)));
            put(3, new Ticket(3, events.get(2), 80d, users.get(2)));
        }
    };

    public static Map<Integer, User> getUsers() {
        return users;
    }

    public static Map<Integer, Event> getEvents() {
        return events;
    }

    public static Map<Integer, Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public static Map<Integer, Ticket> getTickets() {
        return tickets;
    }
}
