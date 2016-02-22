package net.lelyak.edu.tests;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.EventRating;
import org.springframework.dao.EmptyResultDataAccessException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class EventServicesTestCase extends BaseTest {

    @Test(expectedExceptions = EmptyResultDataAccessException.class)
    public void testCreateAndRemoveNewEvent() throws Exception {
        String testEventName = "homework";
        int testEventId = 7;

        Event testEvent = new Event();
        testEvent.setId(testEventId);
        testEvent.setName(testEventName);
        testEvent.setPrice(50d);
        testEvent.setEventRating(EventRating.LOW);

        eventService.create(testEvent);
        Event actualResult = eventService.getEventByName(testEventName);
        assertNotNull(actualResult);

        String actualName = actualResult.getName();
        assertEquals(actualName, testEventName);

        eventService.remove(testEvent);
        eventService.getById(testEventId); // result is not presented at DB we expect to catch exception
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
