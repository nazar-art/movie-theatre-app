package net.lelyak.edu.tests;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class EventServicesTestCase extends BaseTest {

    private Event testEvent;
    private User adminUser;

    @Test(expectedExceptions = EmptyResultDataAccessException.class)
    public void testCreateAndRemoveNewEvent() throws Exception {
        testEvent = generator.getRandomEvent();
        adminUser = userService.getById(1);

        eventService.create(testEvent, adminUser);
        Event actualResult = eventService.getEventByName(testEvent.getName());
        assertNotNull(actualResult);

        String actualName = actualResult.getName();
        assertEquals(actualName, testEvent.getName());

        eventService.remove(testEvent);
        eventService.getById(testEvent.getId()); // result is not presented at DB we expect to catch exception
    }

    @Test
    public void testGetById() throws Exception {
        testEvent = generator.getRandomEvent();
        adminUser = userService.getById(1);
        eventService.create(testEvent, adminUser);

        Event eventById = eventService.getById(testEvent.getId());
        assertNotNull(eventById);
        assertEquals(eventById.getName(), testEvent.getName());
        assertEquals(eventById.getEventRating(), testEvent.getEventRating());
        assertEquals(eventById.getPrice(), testEvent.getPrice());
    }

    @Test/*(enabled = false)*/
    public void testGetByName() throws Exception {
        testEvent = generator.getRandomEvent();
        adminUser = userService.getById(1);
        eventService.create(testEvent, adminUser);

        Event event = eventService.getEventByName(testEvent.getName());

        // just for testing aspect
//        eventService.getEventByName(expectedName);
//        eventService.getEventByName(expectedName);
//        eventService.getEventByName(expectedName);
        //
        assertNotNull(event);
        assertEquals(event.getName(), testEvent.getName());
        assertEquals(event.getId(), testEvent.getId());
        assertEquals(event.getPrice(), testEvent.getPrice());
    }

}
