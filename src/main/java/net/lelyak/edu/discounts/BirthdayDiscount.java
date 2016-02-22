package net.lelyak.edu.discounts;


import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthdayDiscount implements IDiscountStrategy {

	private static final double DISCOUNT = .05;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	@Override
    public double getDiscount(User user, Event event, Date date) {
        Date birthday = user.getBirthday();
        Date now = new Date();

        String userBD = dateFormat.format(birthday);
        String todayDate = dateFormat.format(now);

        if (userBD.equalsIgnoreCase(todayDate)) {
            return DISCOUNT;
        }
        return 0;
	}

}
