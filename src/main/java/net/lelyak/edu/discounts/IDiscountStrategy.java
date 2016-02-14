package net.lelyak.edu.discounts;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.User;

import java.util.Calendar;

public interface IDiscountStrategy {
    double getDiscount(User user, Event event, Calendar date);
}
