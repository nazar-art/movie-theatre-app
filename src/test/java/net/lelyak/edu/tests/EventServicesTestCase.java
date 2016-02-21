package net.lelyak.edu.tests;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.EventRating;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EventServicesTestCase extends BaseTest {

    @Test
    public void testCreateAndRemoveNewEvent() throws Exception {
        String eventName = "homework";
        int eventId = 7;

        Event testEvent = new Event(eventName, 1d, EventRating.LOW);
        testEvent.setId(eventId);

        eventService.create(testEvent);
        Event actualResult = eventService.getEventByName(eventName);
        assertNotNull(actualResult);

        String actualName = actualResult.getName();
        assertEquals(actualName, eventName);

        eventService.remove(testEvent);
        assertNull(eventService.getById(eventId), "event is not removed");
    }

    @Test
    public void testGetById() throws Exception {
        Event eventById = eventService.getById(1);
        assertNotNull(eventById);
    }

    @Test/*(enabled = false)*/
    public void testGetByName() throws Exception {
        String expectedName = "Green Mile";
        Event event = eventService.getEventByName(expectedName);
        // just for testing aspect
//        eventService.getEventByName(expectedName);
//        eventService.getEventByName(expectedName);
//        eventService.getEventByName(expectedName);
        //
        assertNotNull(event);
        assertEquals(event.getName(), expectedName, "names should be the same");
    }

}
