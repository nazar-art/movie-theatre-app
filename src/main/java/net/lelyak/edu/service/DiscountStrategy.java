package net.lelyak.edu.service;

import net.lelyak.edu.entity.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * DiscountStrategy - single class with logic for calculating discount
 * Birthday strategy - give 5% if user has birthday
 * Every 10th ticket - give 50% for every 10th ticket purchased by user
 */
public class DiscountStrategy {

    private User user;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public DiscountStrategy(User user) {
        this.user = user;
    }

    public boolean birthdayDiscount() {
        boolean discount = false;
        Calendar birthday = user.getBirthday();
        Calendar today = Calendar.getInstance();

        String userBD = dateFormat.format(birthday);
        String todayDate = dateFormat.format(today);

        if (userBD.equalsIgnoreCase(todayDate)) {
            discount = true;
        }
        return discount;
    }

    public boolean isHalfTicketDiscount(int ticketNumber) {
        int size = user.getBookedTickets().size();
        return (size % ticketNumber) == 0;
    }

}
