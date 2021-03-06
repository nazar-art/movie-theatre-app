package net.lelyak.edu.configurations;

import net.lelyak.edu.entity.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

//@Configuration
/*@PropertySource({
        "classpath:properties/auditorium1.properties",
        "classpath:properties/auditorium2.properties",
        "classpath:properties/auditorium3.properties",
        "classpath:db/db.properties"})*/
public class AuditoriumConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    private Auditorium createAuditorium(String prefix) {
        String nameString = env.getProperty(prefix + ".name");
        String strNumberOfSeats = env.getProperty(prefix + ".number-of-seats");
        Integer[] integers = env.getProperty(prefix + ".vip-seats", Integer[].class);
        Set<Integer> integerSet = Arrays.stream(integers).collect(Collectors.toSet());

        Auditorium auditorium = new Auditorium();
        auditorium.setName(nameString);
        auditorium.setSeats(Integer.valueOf(strNumberOfSeats));
//        auditorium.setVip(integerSet);
        return auditorium;
    }

    @Bean
    public Auditorium auditorium1() {
        return createAuditorium("auditorium1");
    }

    @Bean
    public Auditorium auditorium2() {
        return createAuditorium("auditorium2");
    }

    @Bean
    public Auditorium auditorium3() {
        return createAuditorium("auditorium3");
    }
}
