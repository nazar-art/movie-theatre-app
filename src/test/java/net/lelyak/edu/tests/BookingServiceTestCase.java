package net.lelyak.edu.tests;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.CommonIndexes;
import net.lelyak.edu.utils.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class BookingServiceTestCase extends BaseTest {

    private User user;
    private Ticket ticket;

    @BeforeMethod(description = "prepare some test data")
    public void setUp() {
        user = userService.getById(CommonIndexes.THREE.getIndex());
        Set<Ticket> bookedTickets = user.getBookedTickets();
        ticket = bookedTickets.iterator().next();
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

        User user = userService.getById(1);

        Set<Event> events = eventService.getAll();
        Event event = events.iterator().next();
        event.setName(testEventName);
        event.setPrice(testEventPrice);

        Ticket testTicket = new Ticket();
        testTicket.setEvent(event);
        testTicket.setPrice(60D);

        bookingService.bookTicket(user, testTicket);

        Set<Ticket> userTickets = user.getBookedTickets();
        Optional<Ticket> bookedTestTicket = userTickets.stream()
                .filter(ticket -> ticket.getEvent().getName().equalsIgnoreCase(testEventName)
                        && ticket.getEvent().getPrice().equals(testEventPrice))
                .findAny();

        assertTrue(bookedTestTicket.isPresent(), "ticket is not booked");

        Logger.info("Final ticket price is: " + testTicket.getPrice());
    }
}
