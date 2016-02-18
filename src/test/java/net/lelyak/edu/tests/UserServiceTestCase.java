package net.lelyak.edu.tests;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.CommonIndexes;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.*;

public class UserServiceTestCase extends BaseTest {

    public static final String RON_WEASLEY_NAME = "Ron Weasley";
    public static final String TEST_EMAIL = "grendjer@gmail.com";
    public static final String GERMIONA_GRENDJER_NAME = "Germiona Grendjer";
    public static final String GARRY_POTTER_NAME = "Garry Potter";
    public static final String CHRISTOFOR_NAME = "Christofor";

    @Test
    public void testGetUserByMail() throws Exception {
        User byEmail = userService.getByEmail(TEST_EMAIL);
        String actualName = byEmail.getName();

        assertEquals(actualName, GERMIONA_GRENDJER_NAME, "first name is not as expected");
    }

    @Test
    public void testUserById() throws Exception {
        User userById = userService.getById(CommonIndexes.ONE.getIndex());

        String actualFirstName = userById.getName();

        assertEquals(actualFirstName, GARRY_POTTER_NAME);
    }

    @Test
    public void testGetUserByName() throws Exception {
        User ron = userService.getByName(RON_WEASLEY_NAME);
        String lastName = ron.getName();
        assertEquals(lastName, RON_WEASLEY_NAME);
    }

    @Test
    public void testRegisterAndRemoveNewUser() throws Exception {
        int userIndex = CommonIndexes.NINE.getIndex();
        String userName = CHRISTOFOR_NAME;

        User newUser = new User(userName);
        newUser.setId(userIndex);
        userService.register(newUser);

        User createdUser = userService.getById(userIndex);
        assertEquals(createdUser.getName(), userName);

        userService.remove(newUser);

        assertNull(userService.getById(userIndex), "user can't be returned it should be null");
    }

    @Test
    public void testGetUserBookedTickets() throws Exception {
        int userIndex = CommonIndexes.THREE.getIndex();
        User testUser = userService.getById(userIndex);

        Set<Ticket> bookedTickets = userService.getBookedTickets(testUser);
        assertNotNull(bookedTickets, "booked tickets for user can not be null");
    }
}
