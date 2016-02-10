package net.lelyak.edu.discounts;


import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BirthdayDiscount {

	private static final double DISCOUNT = .05;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	public double getDiscount(User user, Event event, Calendar date) {
        Calendar birthday = user.getBirthday();
        Calendar today = Calendar.getInstance();

        String userBD = dateFormat.format(birthday);
        String todayDate = dateFormat.format(today);

        if (userBD.equalsIgnoreCase(todayDate)) {
            return DISCOUNT;
        }
        return 0;
	}

}
