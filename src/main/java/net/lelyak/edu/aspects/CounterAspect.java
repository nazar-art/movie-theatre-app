package net.lelyak.edu.aspects;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.SeatType;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * CounterAspect - count how many times each event was accessed by name,
 * how many times its prices were queried,
 * how many times its tickets were booked.
 *
 * Store counters in map for now (later could be replaced by DB dao)
 */
@Aspect
public class CounterAspect {

    private Map<Event, Integer> eventCallByNameMap = new HashMap<>();
    private Map<Event, Integer> eventPriceCallMap = new HashMap<>();
    private Map<Event, Integer> bookTicketCallMap = new HashMap<>();

    @Pointcut("execution(* *..EventService.getByName(..))")
    public void accessEventByName() {
    }

    @Pointcut("execution(* *..BookingService.getTicketPrice(..))")
    public void eventPriceQueryCounter() {
    }

    @Pointcut("execution(* *..BookingService.bookTicket(..))")
    public void ticketBookingCounterForEvent() {
    }


    @AfterReturning(
            pointcut = "accessEventByName()",
            returning = "event"
    )
    public void countEventCallByName(Event event) {

        if (!eventCallByNameMap.containsKey(event)) {

            eventCallByNameMap.put(event, 1);
            Logger.info(String.format("Event: %s is called FIRST time", event.getName()));
        } else {

            Integer oldValue = eventCallByNameMap.get(event);
            Integer newValue = oldValue + 1;
            eventCallByNameMap.put(event, newValue);
            Logger.info(String.format("Event: %s is called: %s times", event.getName(), newValue));
        }
    }

    @AfterReturning(
            pointcut = "eventPriceQueryCounter() && args(event, dateTime, seatType, user)",
            argNames = "joinPoint,event,dateTime,seatType,user")
    public void countEventPriceQueryCounter(JoinPoint joinPoint, Event event, Calendar dateTime, SeatType seatType, User user) {

        if (!eventPriceCallMap.containsKey(event)) {
            eventPriceCallMap.put(event, 1);
            Logger.info(String.format("Ticket price for Event: %s is called FIRST time",
                    event.getName()));

        } else {
            Integer oldIndex = eventPriceCallMap.get(event);
            int newIndex = oldIndex + 1;
            eventPriceCallMap.put(event, newIndex);
            Logger.info(String.format("Ticket price for Event: %s is called: %s times",
                    event.getName(), newIndex));
        }

    }

    @AfterReturning(
            pointcut = "ticketBookingCounterForEvent() && args(user, ticket)",
            argNames = "joinPoint,user,ticket")
    public void countTicketBookingForEvent(JoinPoint joinPoint, User user, Ticket ticket) {
        Event event = ticket.getEvent();

        if (!bookTicketCallMap.containsKey(event)) {
            bookTicketCallMap.put(event, 1);
            Logger.info(String.format("Book ticket for Event: %s is called FIRST time", event.getName()));

        } else {
            Integer oldIndex = bookTicketCallMap.get(event);
            int newIndex = oldIndex + 1;
            bookTicketCallMap.put(event, newIndex);
            Logger.info(String.format("Book Ticket for Event: %s is called: %s times", event.getName(), newIndex));        }
    }

    public Map<Event, Integer> getEventCallByNameMap() {
        return eventCallByNameMap;
    }

    public Map<Event, Integer> getEventPriceCallMap() {
        return eventPriceCallMap;
    }

    public Map<Event, Integer> getBookTicketCallMap() {
        return bookTicketCallMap;
    }
}
