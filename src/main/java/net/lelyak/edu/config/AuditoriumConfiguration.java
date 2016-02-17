package net.lelyak.edu.config;

import net.lelyak.edu.entity.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

@Configuration
@PropertySource({"classpath:properties/auditorium1.properties",
        "classpath:properties/auditorium2.properties",
        "classpath:properties/auditorium3.properties",
        "classpath:db/dp.properties"})
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
        String vipSeatsString = env.getProperty(prefix + ".vip-seats");
        Set<Integer> integerSet = processVipSeats(vipSeatsString);

        Auditorium auditorium = new Auditorium();
        auditorium.setName(nameString);
        auditorium.setNumberOfSeats(Integer.valueOf(strNumberOfSeats));
        auditorium.setVipSeats(integerSet);
        return auditorium;
    }

    private Set<Integer> processVipSeats(String vipSeatsString) {
        Set<Integer> result = new HashSet<>();
        StringTokenizer tokenizer = new StringTokenizer(vipSeatsString, ",");
        while (tokenizer.hasMoreTokens()) {
            String s = tokenizer.nextToken();
            result.add(Integer.valueOf(s));
        }
        return result;
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
