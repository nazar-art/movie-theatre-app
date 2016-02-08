package net.lelyak.edu.dao.mock;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.User;

import java.util.HashMap;
import java.util.Map;

public class DatabaseMock {

    private static Map<Integer, User> users = new HashMap<>();
    private static Map<Integer, Event> events = new HashMap<>();

    public static Map<Integer, User> getUsers() {
        return users;
    }

    public static Map<Integer, Event> getEvents() {
        return events;
    }
}
