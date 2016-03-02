package net.lelyak.edu;

import net.lelyak.edu.aspects.CounterAspect;
import net.lelyak.edu.aspects.DiscountAspect;
import net.lelyak.edu.aspects.LuckyWinnerAspect;
import net.lelyak.edu.repository.AuditoriumRepository;
import net.lelyak.edu.repository.EventRepository;
import net.lelyak.edu.repository.TicketRepository;
import net.lelyak.edu.repository.UserRepository;
import net.lelyak.edu.service.*;
import net.lelyak.edu.utils.TestNGListener;
import net.lelyak.edu.utils.datafactory.GeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;

@Listeners({TestNGListener.class,
        org.uncommons.reportng.HTMLReporter.class,
        org.uncommons.reportng.JUnitXMLReporter.class})
@ComponentScan(basePackages = "net.lelyak.edu")
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class BaseTest extends AbstractTestNGSpringContextTests {

//    @Autowired
//    private EmbeddedDatabase db;

    /*@BeforeSuite(description = "Initialize Embedded DB instance, create and populate tables")
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
        db = new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
                .setType(EmbeddedDatabaseType.DERBY)
                .setName("moviedb")
                .addScript("db/sql/create-db.sql")
                .addScript("db/sql/insert-data.sql")
                .build();
    }*/

    @Autowired
    protected AuditoriumService auditoriumService;
    @Autowired
    protected BookingService bookingService;
    @Autowired
    protected DiscountService discountService;
    @Autowired
    protected EventService eventService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected TicketService ticketService;

    protected GeneratorRepository generator = new GeneratorRepository();

    @Autowired
    protected CounterAspect counterAspect;
    @Autowired
    protected DiscountAspect discountAspect;
    @Autowired
    protected LuckyWinnerAspect luckyWinnerAspect;

    @Autowired
    protected AuditoriumRepository auditoriumRepository;
    @Autowired
    protected EventRepository eventRepository;
    @Autowired
    protected TicketRepository ticketRepository;
    @Autowired
    protected UserRepository userRepository;

}
