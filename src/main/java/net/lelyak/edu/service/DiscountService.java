package net.lelyak.edu.service;

import net.lelyak.edu.discounts.BirthdayDiscount;
import net.lelyak.edu.discounts.HalfPriceTicketDiscount;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.User;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * DiscountService - Counts different discounts for purchased tickets
 * <p>
 * getDiscount(user, event, date) - returns discount for each ticket for the user on particular event
 * <p>
 * IDiscountStrategy - single class with logic for calculating discount
 * Birthday strategy - give 5% if user has birthday
 * Every 10th ticket - give 50% for every 10th ticket purchased by user
 * <p>
 * All discount strategies should be injected as list into the DiscountService.
 * The getDiscount() method will execute each strategy to get max available discount.
 * <p>
 * Define DiscountService with all strategies as separate beans in separate configuration file
 * (either separate XML or separate Java config class)
 */
@Service
public class DiscountService {

    private BirthdayDiscount birthdayDiscount;
    private HalfPriceTicketDiscount halfPriceTicketDiscount;

    public double getDiscount(User user, Event event, Calendar date) {
        double discount = 1;

        discount *= birthdayDiscount.getDiscount(user, event, date);
        discount *= halfPriceTicketDiscount.getDiscount(user, event, date);

        return 1 - discount;
    }
}
