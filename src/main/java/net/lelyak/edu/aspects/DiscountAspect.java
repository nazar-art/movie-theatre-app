package net.lelyak.edu.aspects;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * DiscountAspect - count how many times each discount was given total and for specific user
 */
@Aspect
public class DiscountAspect {

    private int discountTotalCounter = 0;
    private Map<User, Integer> userDiscountMap = new HashMap<>();

    @Pointcut("execution(* * ..DiscountService.getDiscount(..))")
    public void accessGetDiscount() {
    }


    @AfterReturning(pointcut = "accessGetDiscount() && args(user, event, date)",
            argNames = "user,event,date")
    public void countDiscountCallForeachUser(User user, Event event, Calendar date) {

        if (!userDiscountMap.containsKey(user)) {
            userDiscountMap.put(user, 1);
            Logger.info(String.format("Discount for User: %s is called FIRST time", user.getName()));

        } else {
            Integer oldIndex = userDiscountMap.get(user);
            int newIndex = oldIndex + 1;

            userDiscountMap.put(user, newIndex);
            Logger.info(String.format("Discount for User: %s is called: %s times", user.getName(), newIndex));
        }
    }

    @AfterReturning(pointcut = "accessGetDiscount() && args(user, event, date)",
            returning = "discount", argNames = "user,event,date,discount")
    public void countTotalDiscountCall(User user, Event event, Calendar date, Double discount) {
//        discountTotalCounter = discountTotalCounter + 1;

        discountTotalCounter = userDiscountMap.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        Logger.info(String.format("Discount calculation is called: %s time/s", discountTotalCounter));

    }

    public int getDiscountTotalCounter() {
        return discountTotalCounter;
    }

    public Map<User, Integer> getUserDiscountMap() {
        return userDiscountMap;
    }
}
