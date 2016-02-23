package net.lelyak.edu.service;

import net.lelyak.edu.dao.impl.EventDAO;
import net.lelyak.edu.entity.Auditorium;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * EventService - Manages events (movie shows).
 * Event contains only basic information, like name, base price for tickets, rating (high, mid, low).
 * Event can be presented on several dates and several times within each day.
 * For each dateTime an Event will be presented only in single auditorium.
 * <p>
 * save, remove, getEventByName, getAll
 * assignAuditorium(event, auditorium, date) - assign auditorium for event for specific date.
 * Only one auditorium for Event for specific dateTime
 * <p>
 * getForDateRange(from, to) - returns events for specified date range (OPTIONAL)
 * getNextEvents(to) - returns events from now till the ‘to’ date (OPTIONAL)
 */
@Service
public class EventService {

    @Autowired
    private EventDAO eventDao;

//    private Map<Integer, Event> events = DatabaseMock.getEvents();


    public EventService() {
        /*events.put(1, new Event(1, "Green Mile", 60d, EventRating.HIGH));
        events.put(2, new Event(2, "Gone by the wind", 50d, EventRating.MIDDLE));
        events.put(3, new Event(3, "Mad Max: Furry road", 90d, EventRating.LOW));*/
    }

    /**
     * @param event new event instance.
     * @return newly created event.
     * @deprecated Just use {@link #create(Event, User)} instead.
     * It can check if thisUser has access for event creation.
     *
     * It is used just for test reason.
     */
    @Deprecated
    public int create(Event event) {
//        return events.put(event.getId(), event);
        return eventDao.save(event);
    }

    public int create(Event event, User thisUser) {
        int result = -1;
        if (thisUser.getRole() != null && thisUser.getRole().equalsIgnoreCase("admin")) {

//            events.put(event.getId(), event);
            result = eventDao.save(event);

            Logger.info(String.format("User: %s has already created event: %s", thisUser.getName(), event.getName()));
        } else {
            Logger.warn(String.format("User: %s doesn't have permission for creating events", thisUser.getName()));
        }
        return result;
    }

    public Event getById(int id) {
//        return events.get(id);
        return eventDao.getById(id);
    }

    public void remove(Event event) {
//        return events.remove(event.getId());
        eventDao.delete(event.getId());
    }

    public Set<Event> getAll() {
        /*return events.values().stream()
                .collect(toSet());*/
        return eventDao.getAll().stream()
                .collect(Collectors.toSet());
    }

    public Event getEventByName(String eventName) {
        Logger.info("EventService.getEventByName called for: " + eventName);
        /*return events.values().parallelStream()
                .filter(e -> e.getName() != null
                        && e.getName().equalsIgnoreCase(eventName))
                .findAny()
                .get();*/
        return eventDao.getByName(eventName);
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Calendar date) {
        if (!event.getDateTime().equals(date.getTime())) {
            event.setDateTime(date.getTime());
            Logger.info(String.format("Auditorium: %s is assigned for event: %s on %s",
                    auditorium.getName(), event.getName(), formatDate(date)));
        }

    }

    public Collection<Event> getForDateRange(Date from, Date to) {
        Set<Event> allEvents = getAll();
        return allEvents.stream()
                .filter(event -> event.getDateTime().after(from)
                        && event.getDateTime().before(to))
                .collect(Collectors.toList());
    }

    public Collection<Event> getNextEvents(Date to){
        Date now  = new Date();
        return eventDao.getAll().stream()
                .filter(event -> event.getDateTime().before(to)
                        && event.getDateTime().after(now))
                .collect(Collectors.toList());
    }

    private String formatDate(Calendar date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }

}
