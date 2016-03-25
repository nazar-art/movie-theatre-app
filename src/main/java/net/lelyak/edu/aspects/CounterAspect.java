package net.lelyak.edu.aspects;

import net.lelyak.edu.dao.impl.AspectCounterDAO;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.utils.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * CounterAspect - count how many times each event was accessed by name,
 * how many times its prices were queried,
 * how many times its tickets were booked.
 * <p/>
 * Store counters in map for now (later could be replaced by DB dao)
 */
@Aspect
public class CounterAspect {

    private HashMap<Event, Long> counterEventGetByName = new HashMap<>();
    private HashMap<Event, Long> counterEventGetTicketPrice = new HashMap<>();
    private HashMap<Event, Long> counterEventTicketBooked = new HashMap<>();

    @Autowired
    private AspectCounterDAO aspectCounterDAO;


    @Pointcut("execution(* getByName(..))")
    private void execEventRepositoryGetByName() {
    }

    @Pointcut("target(net.lelyak.edu.repository.EventRepository)")
    private void withinEventRepository() {
    }

    @Pointcut("execution(* getTicketPrice(..)) && args(event, ..)")
    private void execTicketServiceGetTicketPrice(Event event) {
    }

    @Pointcut("target(net.lelyak.edu.service.TicketService)")
    private void withinTicketService() {
    }

    @Pointcut("execution(* *..TicketService.bookTicket(..))")
    private void execEventTicketBooked() {
    }

    @AfterReturning(
            pointcut = "execEventRepositoryGetByName() && withinEventRepository()",
            returning = "event")
    public void afterExecEventRepositoryGetByName(JoinPoint jp, Event event) {

        if (!counterEventGetByName.containsKey(event))
            counterEventGetByName.put(event, 0L);

        counterEventGetByName.put(event, counterEventGetByName.get(event) + 1);
        Logger.debug("afterEventGetByName from " + jp.getTarget().toString() + ", event: " + event);
    }

    @AfterReturning(
            pointcut = "execTicketServiceGetTicketPrice(event)", argNames = "jp,event")
    public void afterTicketServiceGetTicketPrice(JoinPoint jp, Event event) {

        if (!counterEventGetTicketPrice.containsKey(event))
            counterEventGetTicketPrice.put(event, 0L);

        counterEventGetTicketPrice.put(event, counterEventGetTicketPrice.get(event) + 1);
        Logger.debug("afterEventGetTicketPrice  from " + jp.getTarget().toString() + ", event: " + event);
    }

    @AfterReturning(
            pointcut = "execEventTicketBooked()",
            returning = "ticket")
    public void afterEventTicketBooked(JoinPoint jp, Ticket ticket) {

        Event event = ticket.getEvent();

        if (!counterEventTicketBooked.containsKey(event))
            counterEventTicketBooked.put(event, 0L);

        counterEventTicketBooked.put(event, counterEventTicketBooked.get(event) + 1);
        Logger.debug("afterEventTicketBooked  from " + jp.getTarget().toString() + ", event: " + event);
    }

    public HashMap<Event, Long> getCounterEventGetByName() {
        return counterEventGetByName;
    }

    public HashMap<Event, Long> getCounterEventGetTicketPrice() {
        return counterEventGetTicketPrice;
    }

    public HashMap<Event, Long> getCounterEventTicketBooked() {
        return counterEventTicketBooked;
    }











    /*private Map<Long, Integer> eventCallByNameMap = new HashMap<>();
    private Map<Long, Integer> eventPriceCallMap = new HashMap<>();
    private Map<Long, Integer> eventBookTicketCallMap = new HashMap<>();

    *//**
     * Pointcuts:
     *//*
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


    *//**
     * Advices:
     *//*
    @AfterReturning(
            pointcut = "accessEventByName()",
            returning = "event"
    )
    public void countEventCallByName(Event event) {

        if (eventCallByNameMap.containsKey(event.getId())) {
            Integer oldValue = eventCallByNameMap.get(event.getId());
            Integer newValue = oldValue + 1;
            eventCallByNameMap.put(event.getId(), newValue);
            Logger.info(String.format("Event: %s is called by name: %s times", event.getName(), newValue));
        } else {
            eventCallByNameMap.put(event.getId(), 1);
            Logger.info(String.format("Event: %s is called by name FIRST time", event.getName()));
        }
    }

    @AfterReturning(
            pointcut = "accessEventPrice() && args(event, dateTime, seatType, user)",
            argNames = "event,dateTime,seatType,user")
    public void countEventPriceQueryCounter(Event event, Calendar dateTime, SeatType seatType, User user) {

        if (eventPriceCallMap.containsKey(event.getId())) {
            Integer oldIndex = eventPriceCallMap.get(event.getId());
            int newIndex = oldIndex + 1;
            eventPriceCallMap.put(event.getId(), newIndex);
            Logger.info(String.format("Ticket price for Event: %s is called: %s times",
                    event.getName(), newIndex));

        } else {
            eventPriceCallMap.put(event.getId(), 1);
            Logger.info(String.format("Ticket price for Event: %s is called FIRST time",
                    event.getName()));
        }

    }

    @AfterReturning(
            pointcut = "accessEventTicketBooking() && args(user, ticket)",
            argNames = "user,ticket")
    public void countTicketBookingForEvent(User user, Ticket ticket) {
        Event event = ticket.getEvent();

        if (eventBookTicketCallMap.containsKey(event.getId())) {
            Integer oldIndex = eventBookTicketCallMap.get(event.getId());
            int newIndex = oldIndex + 1;
            eventBookTicketCallMap.put(event.getId(), newIndex);
            Logger.info(String.format("Book Ticket for Event: [%s] is called: %s times", event.getName(), newIndex));
        } else {
            eventBookTicketCallMap.put(event.getId(), 1);
            Logger.info(String.format("Book ticket for Event: [%s] is called FIRST time", event.getName()));
        }
    }


    public Map<Long, Integer> getEventCallByNameMap() {
        return eventCallByNameMap;
    }

    public Map<Long, Integer> getEventPriceCallMap() {
        return eventPriceCallMap;
    }

    public Map<Long, Integer> getEventBookTicketCallMap() {
        return eventBookTicketCallMap;
    }*/
}
