package net.lelyak.edu.dao.mock;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Rating;
import net.lelyak.edu.entity.User;

import java.util.HashMap;
import java.util.Map;

public class DatabaseMock {

    private static Map<Integer, User> users = new HashMap<>();

    private static Map<Integer, Event> events = new HashMap<Integer, Event>() {
        {
            put(1, new Event("Green Mile", 60, Rating.HIGH));
            put(1, new Event("Gone by the wind", 50, Rating.MIDDLE));
            put(1, new Event("Mad Max: Furry road", 90, Rating.LOW));
        }
    };

    public static Map<Integer, User> getUsers() {
        return users;
    }

    public static Map<Integer, Event> getEvents() {
        return events;
    }
}
