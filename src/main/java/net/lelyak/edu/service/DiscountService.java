package net.lelyak.edu.service;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.User;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * DiscountService - Counts different discounts for purchased tickets
 *
 * getDiscount(user, event, date) - returns discount for each ticket for the user on particular event
 *
 * DiscountStrategy - single class with logic for calculating discount
 * Birthday strategy - give 5% if user has birthday
 * Every 10th ticket - give 50% for every 10th ticket purchased by user
 *
 * All discount strategies should be injected as list into the DiscountService.
 * The getDiscount() method will execute each strategy to get max available discount.
 *
 * Define DiscountService with all strategies as separate beans in separate configuration file
 * (either separate XML or separate Java config class)
 */
@Service
public class DiscountService {

    public static final int HALF_PRICE_TICKET_DELIMITER = 10;
    public static final double TEN_PERCENT_DISCOUNT = .9;
    public static final double HALF_PRICE_DISCOUNT = .5;

    public Double getDiscount(User user, Event event, Calendar date) {
        DiscountStrategy strategy = new DiscountStrategy(user);
        Double eventPrice = event.getPrice();
        boolean hasBirthdayDiscount = strategy.birthdayDiscount();
        boolean hasHalfTicketDiscount = strategy.isHalfTicketDiscount(HALF_PRICE_TICKET_DELIMITER);

        if (hasBirthdayDiscount) {
            eventPrice = eventPrice * TEN_PERCENT_DISCOUNT;
        } else if (hasHalfTicketDiscount) {
            eventPrice = eventPrice * HALF_PRICE_DISCOUNT;
        }
        return eventPrice;
    }
}
