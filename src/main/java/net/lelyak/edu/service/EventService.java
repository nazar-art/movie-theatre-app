package net.lelyak.edu.service;

import net.lelyak.edu.dao.mock.DatabaseMock;
import net.lelyak.edu.entity.Auditorium;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.EventRating;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

/**
 * EventService - Manages events (movie shows).
 * Event contains only basic information, like name, base price for tickets, rating (high, mid, low).
 * Event can be presented on several dates and several times within each day.
 * For each dateTime an Event will be presented only in single auditorium.
 *
 * create, remove, getByName, getAll
 * assignAuditorium(event, auditorium, date) - assign auditorium for event for specific date.
 * Only one auditorium for Event for specific dateTime
 *
 * getForDateRange(from, to) - returns events for specified date range (OPTIONAL)
 * getNextEvents(to) - returns events from now till the ‘to’ date (OPTIONAL)
 */
@Service
public class EventService {

    private Map<Integer, Event> events = DatabaseMock.getEvents();

    public EventService() {
        events.put(1, new Event(1, "Green Mile", 60d, EventRating.HIGH));
        events.put(2, new Event(2, "Gone by the wind", 50d, EventRating.MIDDLE));
        events.put(3, new Event(3, "Mad Max: Furry road", 90d, EventRating.LOW));
    }

    public Event create(Event event) {
        return events.put(event.getId(), event);
    }

    public Event getById(int id) {
        return events.get(id);
    }

    public Event remove(Event event) {
        return events.remove(event.getId());
    }

    public Set<Event> getAll() {
        return events.values().stream()
                .collect(toSet());
    }

    public Event getByName(String eventName) {
        return events.values().stream()
                .filter(e -> e.getName() != null)
                .filter(e -> e.getName().equalsIgnoreCase(eventName))
                .findFirst()
                .get();
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Calendar date) {
        // todo

    }

    public Set<Event> getForDateRange(Calendar from, Calendar to) {
        Set<Event> allEvents = getAll();
        return allEvents.stream()
                .filter(event -> event.getEventDateTime()
                        .stream()
                        .anyMatch(date -> date.after(from) && date.before(to))
                )
                .collect(Collectors.toSet());
    }


}
