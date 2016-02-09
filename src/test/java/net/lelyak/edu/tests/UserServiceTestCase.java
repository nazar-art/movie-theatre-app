package net.lelyak.edu.tests;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Gender;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.CommonIndexes;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class UserServiceTestCase extends BaseTest {

    @Test
    public void testGetUserByMail() throws Exception {
        User byEmail = userService.getByEmail("grendjer@gmail.com");
        String actualName = byEmail.getFirstName();
        String actualLastName = byEmail.getLastName();

        assertEquals(actualName, "Germiona", "first name is not as expected");
        assertEquals(actualLastName, "Grendjer", "last name is not as expected");
    }

    @Test
    public void testUserById() throws Exception {
        User userById = userService.getById(CommonIndexes.ONE.getIndex());

        String actualFirstName = userById.getFirstName();
        String actualLastName = userById.getLastName();
        Gender actualGender = userById.getGender();

        assertEquals(actualFirstName, "Garry");
        assertEquals(actualLastName, "Potter");
        assertEquals(actualGender, Gender.MALE);
    }

    @Test
    public void testGetUserByName() throws Exception {
        User ron = userService.getByName("Ron");
        Gender ronGender = ron.getGender();
        String lastName = ron.getLastName();
        assertEquals(ronGender, Gender.MALE);
        assertEquals(lastName, "Weasley");
    }

    @Test
    public void testRegisterAndRemoveNewUser() throws Exception {
        int userIndex = CommonIndexes.NINE.getIndex();
        String userName = "Christofor";
        String userLastName = "Columb";

        User newUser = new User(userName, userLastName, Gender.MALE);
        newUser.setId(userIndex);
        userService.register(newUser);

        User createdUser = userService.getById(userIndex);
        assertEquals(createdUser.getFirstName(), userName);
        assertEquals(createdUser.getLastName(), userLastName);
        assertEquals(createdUser.getGender(), Gender.MALE);

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
