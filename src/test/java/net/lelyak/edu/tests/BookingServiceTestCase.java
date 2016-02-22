package net.lelyak.edu.tests;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class BookingServiceTestCase extends BaseTest {

    private User user;

    @BeforeClass(description = "prepare some test data")
    public void setUp() {
        user = userService.getById(3);
    }

    @Test
    public void testGetTicketPrice() throws Exception {
        List<Ticket> ticketsForUser = bookingService.getTicketsForUser(user);
        assertNotNull(ticketsForUser);
    }

    @Test
    public void testBookTicket() throws Exception {
        String testEventName = "Test name";
        double testEventPrice = 66D;
        int testTicketId = 678;

//        User user = userService.getById(1);

        Set<Event> events = eventService.getAll();
        Event event = events.iterator().next();
        event.setId(testTicketId);
        event.setName(testEventName);
        event.setPrice(testEventPrice);

        Ticket testTicket = new Ticket();
        testTicket.setId(testTicketId);
        testTicket.setEvent(event);
        testTicket.setPrice(60D);

//        bookingService.bookTicket(user, testTicket);
        ticketService.bookTicket(event, new Date(), user);

//        List<Ticket> ticketsForUser = bookingService.getTicketsForUser(user);
        List<Ticket> ticketsForUser = ticketService.getTicketsForUser(user);

        Optional<Ticket> bookedTestTicket = ticketsForUser.stream()
                .filter(ticket -> ticket.getEvent().getName().equalsIgnoreCase(testEventName))
                .findAny();

        assertTrue(bookedTestTicket.isPresent(), "ticket is not booked");

        Logger.info("Final ticket price is: " + testTicket.getPrice());
    }
}
