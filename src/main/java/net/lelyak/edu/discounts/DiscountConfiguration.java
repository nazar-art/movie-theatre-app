package net.lelyak.edu.discounts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DiscountConfiguration {

    @Bean
    public BirthdayDiscount birthdayDiscount() {
        return new BirthdayDiscount();
    }

    @Bean
    public HalfPriceTicketDiscount halfPriceTicketDiscount() {
        return new HalfPriceTicketDiscount();
    }

    @Bean
    public List<IDiscountStrategy> discountStrategies() {
        return Arrays.asList(birthdayDiscount(), halfPriceTicketDiscount());
    }
}
