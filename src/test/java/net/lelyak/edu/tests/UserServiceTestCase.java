package net.lelyak.edu.tests;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserServiceTestCase extends BaseTest {

    public static final String TEST_EMAIL = "grendjer@gmail.com";
    public static final String TEST_NAME_GERMIONA = "Germiona Grendjer";
    public static final String TEST_NAME_GARRY_POTTER = "Garry Potter";
    public static final String TEST_NAME_RON_WEASLEY = "Ron Weasley";

    public static final String TEST_NAME_CHRISTOFOR_COLUMB = "Christofor Columb";
    public static final String COLUMB_BIRTHDAY_DATE = "31/10/1940";
    public static final int TEST_ID_CHRISTOFOR_COLUMB = 8888;

    @Test
    public void testGetUserByMail() throws Exception {
        User byEmail = userService.getByEmail(TEST_EMAIL);
        String actualName = byEmail.getName();

        assertEquals(actualName, TEST_NAME_GERMIONA, "first name is not as expected");
    }

    @Test
    public void testUserById() throws Exception {
        User userById = userService.getById(1);

        String actualFirstName = userById.getName();

        assertEquals(actualFirstName, TEST_NAME_GARRY_POTTER);
    }

    @Test
    public void testGetUserByName() throws Exception {
        User ron = userService.getByName(TEST_NAME_RON_WEASLEY);
        String lastName = ron.getName();
        assertEquals(lastName, TEST_NAME_RON_WEASLEY);
    }

    @Test(expectedExceptions = EmptyResultDataAccessException.class)
    public void testRegisterAndRemoveNewUser() throws Exception {
        User newUser = new User();
        newUser.setId(TEST_ID_CHRISTOFOR_COLUMB);
        newUser.setName(TEST_NAME_CHRISTOFOR_COLUMB);

        userService.register(newUser, COLUMB_BIRTHDAY_DATE);

        User createdUser = userService.getById(TEST_ID_CHRISTOFOR_COLUMB);
        assertEquals(createdUser.getName(), TEST_NAME_CHRISTOFOR_COLUMB);

        userService.remove(newUser);
        // expecting to catch exception here, coz user is not presented at DB
        userService.getById(TEST_ID_CHRISTOFOR_COLUMB);
    }

    @Test
    public void testUserCount() throws Exception {
        User newUser = new User();
        newUser.setId(TEST_ID_CHRISTOFOR_COLUMB);
        newUser.setName(TEST_NAME_CHRISTOFOR_COLUMB);

        int countBeforeInsert = userService.getTotalUsersCount();
        userService.register(newUser, COLUMB_BIRTHDAY_DATE);
        int countAfterInsert = userService.getTotalUsersCount();
        assertEquals(countAfterInsert, countBeforeInsert + 1, "total user count is not incremented after saving new user");

        userService.remove(newUser);
        int countAfterRemove = userService.getTotalUsersCount();
        assertEquals(countAfterRemove, countBeforeInsert, "total user is not decrementing after removing some user");
    }

    @Test
    public void testGetUserBookedTickets() throws Exception {
        int userIndex = 3;
        User testUser = userService.getById(userIndex);

//        Set<Ticket> bookedTickets = userService.getBookedTickets(testUser);
        List<Ticket> ticketsForUser = bookingService.getTicketsForUser(testUser);
        assertNotNull(ticketsForUser, "booked tickets for user can not be null");
    }
}
