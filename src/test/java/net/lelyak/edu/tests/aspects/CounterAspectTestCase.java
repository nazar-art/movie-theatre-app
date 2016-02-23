package net.lelyak.edu.tests.aspects;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.SeatType;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class CounterAspectTestCase extends BaseTest {

    public static final int TEST_REPEAT_COUNTER = 10;

    private List<Event> eventList = new ArrayList<>();

    @Test(priority = -2, alwaysRun = true)
    public void testCallCounterAspectByNameCounter() throws Exception {

        for (int i = 0; i < TEST_REPEAT_COUNTER; i++) {
            Event testEvent = generator.getRandomEvent();
            eventList.add(testEvent);
            eventService.create(testEvent);
            eventService.getEventByName(testEvent.getName());
        }

        Map<Integer, Integer> eventCallByNameMap = counterAspect.getEventCallByNameMap();

        assertNotNull(eventCallByNameMap);
        assertFalse(eventCallByNameMap.isEmpty());
        assertEquals(eventCallByNameMap.size(), TEST_REPEAT_COUNTER, "getEventByName is not called as was expected");
    }

    @Test(dependsOnMethods = "testCallCounterAspectByNameCounter")
    public void testCallCounterAspectPriceCounter() throws Exception {

        for (Event event : eventList) {
            bookingService.getTicketPrice(event, null, SeatType.VIP, generator.getRandomUser());
        }

        Map<Integer, Integer> eventPriceCallMap = counterAspect.getEventPriceCallMap();

        assertNotNull(eventPriceCallMap);
        assertFalse(eventPriceCallMap.isEmpty());
        assertEquals(eventPriceCallMap.size(), TEST_REPEAT_COUNTER);
    }

    @Test
    public void testCallBookTicketAspectCounter() throws Exception {

        for (int i = 0; i < TEST_REPEAT_COUNTER; i++) {
            User user = generator.getRandomUser();
            Ticket ticket = generator.assignRandomTicketToUser(user);
            bookingService.bookTicket(user, ticket);
        }

        Map<Integer, Integer> eventBookTicketCallMap = counterAspect.getEventBookTicketCallMap();

        assertNotNull(eventBookTicketCallMap);
        assertFalse(eventBookTicketCallMap.isEmpty());
        assertEquals(eventBookTicketCallMap.size(), TEST_REPEAT_COUNTER);
    }
}
