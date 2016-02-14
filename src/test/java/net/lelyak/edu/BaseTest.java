package net.lelyak.edu;

import net.lelyak.edu.service.*;
import net.lelyak.edu.utils.TestNGListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Test
@Listeners(TestNGListener.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class BaseTest extends AbstractTestNGSpringContextTests {

    private EmbeddedDatabase db;

    @BeforeClass
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/sql/create-db.sql")
                .addScript("db/sql/insert-data.sql")
                .build();
    }

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

}
