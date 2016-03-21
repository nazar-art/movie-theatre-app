package net.lelyak.edu.tests;

import net.lelyak.edu.tests.util.RandomDate;
import net.lelyak.edu.tests.util.RandomString;
import net.lelyak.edu.utils.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Configuration
@ComponentScan("net.lelyak.edu.tests.util")
public class TestConfig {

    @Bean
    RandomDate randomDate() {
        DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
        RandomDate rd;
        try {
            rd = new RandomDate(df.parse("01.01.1980"), df.parse("31.12.2016"));
        } catch (ParseException e) {
            Logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return rd;
    }

    @Bean
    RandomString randomString() {
        return new RandomString(1, 3, 5, 12);
    }
}
