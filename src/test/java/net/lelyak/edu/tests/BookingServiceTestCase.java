package net.lelyak.edu.tests;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.CommonIndexes;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class BookingServiceTestCase extends BaseTest {

    private User user;
    private Ticket ticket;

    @BeforeMethod
    public void setUp() throws Exception {
        user = userService.getById(CommonIndexes.THREE.getIndex());
        Set<Ticket> bookedTickets = user.getBookedTickets();
        ticket = bookedTickets.iterator().next();
    }

    @Test
    public void testGetTicketPrice() throws Exception {
        List<Ticket> ticketsForUser = bookingService.getTicketsForUser(user);
        Assert.assertNotNull(ticketsForUser);
    }
}
