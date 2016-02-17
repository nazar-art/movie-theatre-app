package net.lelyak.edu.config;

import net.lelyak.edu.entity.Auditorium;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Arrays;
import java.util.List;

//@Configuration
public class AuditoriumConfiguration {

//    @Bean
    public Auditorium auditorium1() {
        return new Auditorium();
    }

//    @Bean
    public Auditorium auditorium2() {
        return new Auditorium();
    }

//    @Bean
    public Auditorium auditorium3() {
        return new Auditorium();
    }

//    @Bean
    public List<Auditorium> auditoriumsList() {
        return Arrays.asList(auditorium1(), auditorium2(), auditorium3());
    }

//    @Bean
    public ConversionService conversionService() {
        return new DefaultConversionService();
    }
}
