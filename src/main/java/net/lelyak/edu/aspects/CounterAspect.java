package net.lelyak.edu.aspects;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.SeatType;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.Logger;
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

    private Map<Integer, Integer> eventCallByNameMap = new HashMap<>();
    private Map<Integer, Integer> eventPriceCallMap = new HashMap<>();
    private Map<Integer, Integer> eventBookTicketCallMap = new HashMap<>();

    /**
     * Pointcuts:
     */
//    @Pointcut("execution(* net.lelyak.edu.service.EventService.getEventByName(..))")
//    @Pointcut("execution(* *..EventService.getEventByName(..))")
//    @Pointcut("execution(* *..EventService.getEventByName(*))")
    @Pointcut("execution(* * ..EventService.getEventByName(..))")
//    @Pointcut("execution(* *.getEventByName(..)) && within(*..EventService)")
    public void accessEventByName() {
    }

    @Pointcut("execution(* * ..BookingService.getTicketPrice(..))")
    public void accessEventPrice() {
    }

    @Pointcut("execution(* * ..BookingService.bookTicket(..))")
    public void accessEventTicketBooking() {
    }


    /**
     * Advices:
     */
    @AfterReturning(
            pointcut = "accessEventByName()",
            returning = "event"
    )
    public void countEventCallByName(Event event) {

        if (!eventCallByNameMap.containsKey(event.getId())) {

            eventCallByNameMap.put(event.getId(), 1);
            Logger.info(String.format("Event: %s is called by name FIRST time", event.getName()));
        } else {

            Integer oldValue = eventCallByNameMap.get(event.getId());
            Integer newValue = oldValue + 1;
            eventCallByNameMap.put(event.getId(), newValue);
            Logger.info(String.format("Event: %s is called by name: %s times", event.getName(), newValue));
        }
    }

    @AfterReturning(
            pointcut = "accessEventPrice() && args(event, dateTime, seatType, user)",
            argNames = "event,dateTime,seatType,user")
    public void countEventPriceQueryCounter(Event event, Calendar dateTime, SeatType seatType, User user) {

        if (!eventPriceCallMap.containsKey(event.getId())) {
            eventPriceCallMap.put(event.getId(), 1);
            Logger.info(String.format("Ticket price for Event: %s is called FIRST time",
                    event.getName()));

        } else {
            Integer oldIndex = eventPriceCallMap.get(event.getId());
            int newIndex = oldIndex + 1;
            eventPriceCallMap.put(event.getId(), newIndex);
            Logger.info(String.format("Ticket price for Event: %s is called: %s times",
                    event.getName(), newIndex));
        }

    }

    @AfterReturning(
            pointcut = "accessEventTicketBooking() && args(user, ticket)",
            argNames = "user,ticket")
    public void countTicketBookingForEvent(User user, Ticket ticket) {
        Event event = ticket.getEvent();

        if (!eventBookTicketCallMap.containsKey(event.getId())) {
            eventBookTicketCallMap.put(event.getId(), 1);
            Logger.info(String.format("Book ticket for Event: [%s] is called FIRST time", event.getName()));

        } else {
            Integer oldIndex = eventBookTicketCallMap.get(event.getId());
            int newIndex = oldIndex + 1;
            eventBookTicketCallMap.put(event.getId(), newIndex);
            Logger.info(String.format("Book Ticket for Event: [%s] is called: %s times", event.getName(), newIndex));        }
    }


    public Map<Integer, Integer> getEventCallByNameMap() {
        return eventCallByNameMap;
    }

    public Map<Integer, Integer> getEventPriceCallMap() {
        return eventPriceCallMap;
    }

    public Map<Integer, Integer> getEventBookTicketCallMap() {
        return eventBookTicketCallMap;
    }
}
