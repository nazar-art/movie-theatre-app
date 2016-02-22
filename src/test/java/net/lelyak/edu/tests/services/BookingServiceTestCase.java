package net.lelyak.edu.tests.services;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.Logger;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class BookingServiceTestCase extends BaseTest {

    private User testUser;

    @Test
    public void testGetTicketPrice() throws Exception {
        testUser = generator.getRandomUser();
        Ticket ticket = generator.assignRandomTicketToUser(testUser);

        userService.register(testUser);
        ticketService.saveTicket(ticket);

        List<Ticket> ticketsForUser = bookingService.getTicketsForUser(testUser);
        assertNotNull(ticketsForUser);
    }

    @Test
    public void testBookTicket() throws Exception {

        testUser = generator.getRandomUser();
        Event testEvent = generator.getRandomEvent();
        Ticket testTicket = generator.assignRandomTicketToUserAndEvent(testUser, testEvent);

        userService.register(testUser);
        eventService.create(testEvent);
        ticketService.saveTicket(testTicket);

        List<Ticket> ticketsForUser = ticketService.getTicketsForUser(testUser);

        Optional<Ticket> bookedTestTicket = ticketsForUser.stream()
                .filter(ticket -> ticket.getEvent().getName().equalsIgnoreCase(testEvent.getName()))
                .findAny();

        assertTrue(bookedTestTicket.isPresent(), "ticket is not booked");

        Logger.info("Final ticket price is: " + testTicket.getPrice());
    }
}
