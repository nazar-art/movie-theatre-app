package net.lelyak.edu.config;

import net.lelyak.edu.entity.Auditorium;
import net.lelyak.edu.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

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
        Logger.warn("VIP values: " + vipSeatsString);

        Auditorium auditorium = new Auditorium();
        auditorium.setName(nameString);
        auditorium.setNumberOfSeats(Integer.valueOf(strNumberOfSeats));
        // todo find solution how to set Set here
//        auditorium.setVipSeats(vipSeatsString, Set.<Integer>.class);
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
