package net.lelyak.edu.tests.services;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.Logger;
import net.lelyak.edu.utils.StringUtilities;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Transactional
public class UserServiceTest extends BaseTest {

    private List<Event> randomEvents() {
        List<Event> testEvents = rtog.randomEventsList(5 + rtog.nextInt(5));
        for (Event event : testEvents) {
            event.setAuditorium(auditoriumService.getRandomAuditorium());
        }
        eventRepository.putAll(testEvents);
        return testEvents;
    }

    private Event randomEvent() {
        Event testEvent = rtog.randomEvent();
        testEvent.setAuditorium(auditoriumService.getRandomAuditorium());
        eventRepository.put(testEvent);
        return testEvent;
    }

    private User randomUser() {
        User testUser = rtog.randomUser();
        userRepository.put(testUser);
        return testUser;
    }

    @Test
    public void testA_SignIn() throws Exception {
        User user = rtog.randomUser();
        userRepository.put(user);
//        assertTrue(user.equals(userService.signIn(user.getName())));
        assertEquals(userService.signIn(user.getName()), user);
    }

    @Test
    public void testB_Register() throws Exception {
        User randomUser = rtog.randomUser();

        User loadedUser = userService.register(randomUser.getName(), randomUser.getEmail(), randomUser.getBirthday());

        Logger.debug(StringUtilities.appendStrings("testB_Register: randomUser=[%s], loadedUser=[%s]", randomUser, loadedUser));
        assertTrue(randomUser.getName().compareTo(loadedUser.getName()) == 0
                && randomUser.getEmail().compareTo(loadedUser.getEmail()) == 0
                && randomUser.getId() == null && loadedUser.getId() != null
        );
    }

    @Test
    public void testC_ViewEvents() throws Exception {
        Collection<Event> testEvents = randomEvents();

        Collection<Event> loadedEvents = userService.viewEvents();

        Logger.debug(StringUtilities.appendStrings("testC_ViewEvents, testEvents=[%s]", testEvents));
        Logger.debug(StringUtilities.appendStrings("testC_ViewEvents, loadedEvents=[%s]", loadedEvents));
        assertTrue(loadedEvents.containsAll(testEvents));
    }

    @Test
    public void testD_BuyTicket() throws Exception {
        Event testEvent = randomEvent();
        User testUser = randomUser();

        Ticket testTicket = userService.buyTicket(testEvent,
                testEvent.getAirDate(),
                testEvent.getAuditorium().getVip(),
                testUser);

        assertEquals(testTicket.getEvent(), testEvent);
        assertEquals(testTicket.getUser(), testUser);
        assertEquals(testTicket.getOnDate(), testEvent.getAirDate());

        assertTrue(ticketRepository.getAll().contains(testTicket));
    }

    @Test
    public void testE_ListTickets() throws Exception {
        List<Ticket> testTickets = new ArrayList<>();
        User testUser = randomUser();

        for (int i = 0; i < 5 + rtog.nextInt(5); i++) {
            Event randomEvent = randomEvent();
            testTickets.add(
                    userService.buyTicket(randomEvent,
                            randomEvent.getAirDate(),
                            randomEvent.getAuditorium().getVip(),
                            testUser));
        }
        Collection<Ticket> loadedTickets = userService.listTickets(testUser);

        assertTrue(loadedTickets.containsAll(testTickets)
                && loadedTickets.size() == testTickets.size());
    }

    @Test
    public void testF_GetPrice() throws Exception {
        Event testEvent1 = randomEvent();
        User testUser1 = randomUser();

        double event1Price = testEvent1.getTicketPrice();
        double userPrice = userService.getPrice(testEvent1, testEvent1.getAirDate(), testUser1);

        Logger.info("event price: " + event1Price);
        Logger.info("user price: " + userPrice);
        assertEquals(event1Price, userPrice, "prices is not equal");

        //BirthdayDiscount
        userPrice = userService.getPrice(testEvent1, testUser1.getBirthday(), testUser1);
        assertEquals(userPrice, event1Price * 0.95, "BirthdayDiscount prices is not the same");

        // 10th ticket
        userPrice = userService.getPrice(testEvent1, testEvent1.getAirDate(), testUser1);
        assertEquals(event1Price, userPrice, "10th ticket prices isn't the same");

        int size = userService.listTickets(testUser1).size();
        for (int i = 0; i < 10 - size - 1; i++) {
            userService.buyTicket(testEvent1, testEvent1.getAirDate(), String.valueOf(i), testUser1);
        }

        userPrice = userService.getPrice(testEvent1, testEvent1.getAirDate(), testUser1);
        assertEquals(userPrice, event1Price * 0.5, "final price check");
    }
}