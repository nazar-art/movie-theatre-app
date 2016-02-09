package net.lelyak.edu;

import net.lelyak.edu.service.*;
import net.lelyak.edu.utils.TestNGListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Test
@Listeners(TestNGListener.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
public class BaseTest extends AbstractTestNGSpringContextTests {

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
