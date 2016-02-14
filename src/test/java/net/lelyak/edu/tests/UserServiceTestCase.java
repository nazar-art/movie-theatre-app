package net.lelyak.edu.tests;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.CommonIndexes;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.*;

public class UserServiceTestCase extends BaseTest {

    @Test
    public void testGetUserByMail() throws Exception {
        User byEmail = userService.getByEmail("grendjer@gmail.com");
        String actualName = byEmail.getName();

        assertEquals(actualName, "Germiona Grendjer", "first name is not as expected");
    }

    @Test
    public void testUserById() throws Exception {
        User userById = userService.getById(CommonIndexes.ONE.getIndex());

        String actualFirstName = userById.getName();
//        Gender actualGender = userById.getGender();

        assertEquals(actualFirstName, "Garry Potter");
//        assertEquals(actualGender, Gender.MALE);
    }

    @Test
    public void testGetUserByName() throws Exception {
        User ron = userService.getByName("Ron Weasley");
//        Gender ronGender = ron.getGender();
        String lastName = ron.getName();
//        assertEquals(ronGender, Gender.MALE);
        assertEquals(lastName, "Ron Weasley");
    }

    @Test
    public void testRegisterAndRemoveNewUser() throws Exception {
        int userIndex = CommonIndexes.NINE.getIndex();
        String userName = "Christofor";

        User newUser = new User(userName/*, Gender.MALE*/);
        newUser.setId(userIndex);
        userService.register(newUser);

        User createdUser = userService.getById(userIndex);
        assertEquals(createdUser.getName(), userName);
//        assertEquals(createdUser.getGender(), Gender.MALE);

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
