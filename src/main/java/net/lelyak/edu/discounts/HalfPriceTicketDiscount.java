package net.lelyak.edu.discounts;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Calendar;
import java.util.List;

public class HalfPriceTicketDiscount implements IDiscountStrategy {

    private final static double HALF_PRICE_DISCOUNT = .5;

    @Autowired
    @Qualifier(value = "bookingService")
    private BookingService bookingServices;

    @Override
    public double getDiscount(User user, Event event, Calendar date) {
        List<Ticket> tickets = bookingServices.getTicketsForUser(user);

        if (!tickets.isEmpty()
                && (tickets.size() + 1) % 10 == 0)
            return HALF_PRICE_DISCOUNT;
        return 0;
    }
}
