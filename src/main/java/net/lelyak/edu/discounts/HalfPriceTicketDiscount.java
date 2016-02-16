package net.lelyak.edu.discounts;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.service.BookingService;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

public class HalfPriceTicketDiscount implements IDiscountStrategy {

    private final static double HALF_PRICE_DISCOUNT = .5;

    @Resource(name = "bookingService")
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
