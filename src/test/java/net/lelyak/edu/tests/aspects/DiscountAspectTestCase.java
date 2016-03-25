package net.lelyak.edu.tests.aspects;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.aspects.DiscountAspect.Record;
import net.lelyak.edu.discounts.IDiscountStrategy;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertTrue;


public class DiscountAspectTestCase extends BaseTest {

    @Test
    public void testAfterDiscountStrategyGetDiscount() {
        User user = rtog.randomUser();
        Event event = rtog.randomEvent();
        String sits = rtog.getRandomSentence();

        userRepository.put(user);
        event.setAuditorium(auditoriumService.getRandomAuditorium());
        eventRepository.put(event);

        Logger.debug("testAfterDiscountStrategyGetDiscount");
        //birthday strategy - 1 time
        Logger.debug("birthdayDiscountStrategy, discount = " + ticketService.getTicketPrice(event, user.getBirthday(), user));

        //10 ticket strategy
        for (int i = 0; i < 9; i++) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setUser(user);
            ticket.setPrice(event.getTicketPrice());
            ticket.setOnDate(event.getAirDate());
            ticket.setName(sits);
            ticketRepository.put(ticket);
            Logger.debug("Adding ticket to ticketRepository " + ticket.toString());
        }

        Logger.debug("every10TicketDiscountStrategy, discount = " + ticketService.getTicketPrice(event, event.getAirDate(), user));

        HashMap<IDiscountStrategy, Record> discountCounter = discountAspect.getDiscountCounter();
        Logger.debug("discountCounter:");
        long sum = 0;
        for (IDiscountStrategy ds : discountCounter.keySet()) {
            Record record = discountCounter.get(ds);
            Logger.debug("  Strategy " + ds.getClass().getName() + "  totalCount=" + record.getTotalCount());
            sum += record.getTotalCount();
            for (User u : record.getCountPerUser().keySet()) {
                Logger.debug("    for user " + u.getName() + ": " + record.getCountPerUser().get(u));
                sum += record.getCountPerUser().get(u);
            }
        }
        Logger.debug("Summa = " + sum);
        assertTrue(sum == 4, "summa isn't as expected");
    }

}
