package net.lelyak.edu.repository;

import net.lelyak.edu.dao.impl.EventDAO;
import net.lelyak.edu.entity.Auditorium;
import net.lelyak.edu.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


public class EventRepository extends BaseRepository<Event, EventDAO> {
    // delete, getByName, getAll
    // getForDateRange(from, to) - returns events for specified date range
    // (OPTIONAL)
    // getNextEvents(to) - returns events from now till the ‘to’ date (OPTIONAL)
    // assignAuditorium(event, auditorium, date) - assign auditorium for event
    // for specific date
    @Autowired
    private AuditoriumRepository auditoriumRepository;

    public Collection<Event> getForDateRange(Date from, Date to) {
        Collection<Event> matchedEvents = getDao().getAll().stream()
                .filter(e -> e.getAirDate().after(from) && e.getAirDate().before(to)).collect(Collectors.toList());

        return matchedEvents;
    }

    @Override
    public int put(Event entity) {
        auditoriumRepository.put(entity.getAuditorium());
        return super.put(entity);
    }

    @Override
    public Event getById(long id) {
        return initEventAuditorium(super.getById(id));
    }

    @Override
    public Event getByName(String name) {
        return initEventAuditorium(super.getByName(name));
    }

    @Override
    public Collection<Event> getAll() {
        return super.getAll();
    }

    @Override
    public void putAll(Collection<Event> entities) {
        super.putAll(entities);
    }

    public Collection<Event> getNextEvents(Date to) {
        Date now = new Date();

        Collection<Event> matchedEvents = getDao().getAll().stream()
                .filter(e -> e.getAirDate().before(to) && e.getAirDate().after(now)).collect(Collectors.toList());

        return matchedEvents;
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
        event.setAuditorium(auditorium);
    }

    private Event initEventAuditorium(Event event) {
        if (event != null) {
            if (event.getAuditorium_id() != null) {
                event.setAuditorium(auditoriumRepository.getById(event.getAuditorium_id()));
            }
        }
        return event;
    }

    private Event saveEventAuditorium(Event event) {
        if (event != null) {
            if (event.getAuditorium() != null) {
                auditoriumRepository.put(event.getAuditorium());
                event.setAuditorium_id(event.getAuditorium().getId());
            }
        }
        return event;
    }

    @Override
    public Event preSave(Event entity) {
        return saveEventAuditorium(entity);
    }

    @Override
    public Event postLoad(Event entity) {
        return initEventAuditorium(entity);
    }
}
