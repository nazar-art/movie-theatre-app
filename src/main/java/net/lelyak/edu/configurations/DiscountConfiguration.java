package net.lelyak.edu.configurations;

import net.lelyak.edu.discounts.BirthdayDiscount;
import net.lelyak.edu.discounts.HalfPriceTicketDiscount;
import net.lelyak.edu.discounts.IDiscountStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DiscountConfiguration {

    @Bean
    public IDiscountStrategy birthdayDiscount() {
        return new BirthdayDiscount();
    }

    @Bean
    public IDiscountStrategy halfPriceTicketDiscount() {
        return new HalfPriceTicketDiscount();
    }

    @Bean
    public List<IDiscountStrategy> discountStrategies() {
        return Arrays.asList(birthdayDiscount(), halfPriceTicketDiscount());
    }
}
