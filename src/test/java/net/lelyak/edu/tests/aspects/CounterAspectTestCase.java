package net.lelyak.edu.tests.aspects;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.tests.util.AspectTestUtil;
import net.lelyak.edu.utils.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertTrue;


public class CounterAspectTestCase extends BaseTest {

    private AspectTestUtil atu = new AspectTestUtil();

    @Test
    public void testAfterEventGetByName() {
        HashMap<Event, Long> testEventCounts = randomEventsMap();
        eventRepository.putAll(testEventCounts.keySet());

        Logger.debug("\n================== testAfterEventGetByName: ==================");
        Logger.debug("\n testEventCounts: ");

        for (Event event : testEventCounts.keySet()) {
            Logger.debug("Event getByName for " + event.toString());
            for (int i = 0; i < testEventCounts.get(event); i++) {
                Logger.debug(eventRepository.getByName(event.getName()).equals(event) ? " + " : " - ");
            }
            Logger.debug("");
        }

        HashMap<Event, Long> counterAspectEventGetByName = counterAspect.getCounterEventGetByName();

        atu.printCounter(counterAspectEventGetByName);

        assertTrue(atu.compareEventCounts(counterAspectEventGetByName, testEventCounts));
    }

    @Test
    public void testAfterEventGetTicketPrice() {
        HashMap<Event, Long> testEventCounts = randomEventsMap();

        User user = randomUser();

        Logger.debug("\n================== testAfterEventGetTicketPrice: ==================");
        Logger.debug("\n testEventCounts: ");
        for (Event event : testEventCounts.keySet()) {
            Logger.debug("Event " + event.getName());
            for (int i = 0; i < testEventCounts.get(event); i++) {
                Logger.debug(" " + ticketService.getTicketPrice(event, event.getAirDate(), user) + " ");
            }
            Logger.debug("\n");
        }

        HashMap<Event, Long> counterAspectEventGetTicketPrice = counterAspect.getCounterEventGetTicketPrice();

        atu.printCounter(counterAspectEventGetTicketPrice);
        //check if counts matches
        assertTrue(atu.compareEventCounts(counterAspectEventGetTicketPrice, testEventCounts));
    }

    private HashMap<Event, Long> randomEventsMap() {
        HashMap<Event, Long> testEventCounts = new HashMap<Event, Long>();
        for (Event e : rtog.randomEventsList(5 + rtog.nextInt(5))) {
            e.setAuditorium(auditoriumService.getRandomAuditorium());
            eventRepository.put(e);
            testEventCounts.put(e, (long) (5 + rtog.nextInt(5)));
        }
        eventRepository.putAll(testEventCounts.keySet());
        return testEventCounts;
    }

    @Test
    public void testAfterEventTicketBooked() {
        HashMap<Event, Long> testEventCounts = randomEventsMap();
        User user = randomUser();

        Logger.debug("\n==================  testAfterEventTicketBooked:================== ");
        Logger.debug(" testEventCounts: ");
        for (Event event : testEventCounts.keySet()) {

            for (int i = 0; i < testEventCounts.get(event); i++) {
                Logger.debug("Ticket for event '" + event.getName()
                        + "' booked = " + ticketService.bookTicket(
                        event,
                        rtog.getRandomDate(),
                        rtog.getRandomSentence(),
                        user));
            }
        }
        HashMap<Event, Long> counterAspectEventTicketBooked = counterAspect.getCounterEventTicketBooked();

        atu.printCounter(counterAspectEventTicketBooked);

        assertTrue(atu.compareEventCounts(counterAspectEventTicketBooked, testEventCounts));
    }

    private User randomUser() {
        User user = rtog.randomUser();
        userRepository.put(user);
        return user;
    }
}
