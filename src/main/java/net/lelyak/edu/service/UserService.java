package net.lelyak.edu.service;

import net.lelyak.edu.dao.mock.DatabaseMock;
import net.lelyak.edu.entity.Gender;
import net.lelyak.edu.entity.Role;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.CommonIndexes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * UserService - Manages registered users
 * register, remove, getById, getUserByEmail, getUsersByName, getBookedTickets
 */
public class UserService {
    private final int USER = CommonIndexes.ONE.getIndex();
    private Map<Integer, User> users = DatabaseMock.getUsers();

    public UserService() {
        users.put(1, new User(1, "Garry", "Potter", Gender.MALE));
        users.put(2, new User(2, "Ron", "Weasley", Gender.MALE));
        users.put(3, new User(3, "Germiona", "Grendjer", "grendjer@gmail.com",
                new HashSet<Ticket>(Collections.singletonList(
                        new Ticket(DatabaseMock.getEvents().get(1), 30, users.get(1))))));
    }

    public User register(User user) {
        return users.put(user.getId(), user);
    }

    public User register(User user, String date, String gender) {
        user.setGender(getGender(gender));
        user.setBirthday(toDateFormat(date));
        Role registeredUser = new Role();
        registeredUser.setId(USER);
        user.setRole(registeredUser);
        return register(user);
    }

    public void remove(User user) {
        users.remove(user.getId());
    }

    public User getById(int id) {
        return users.get(id);
    }

    public User getByEmail(String email) {
        return users.entrySet().stream()
                .filter(e -> e.getValue().getEmail() != null)
                .filter(e -> e.getValue().getEmail().equalsIgnoreCase(email))
                .findFirst()
                .get()
                .getValue();
        /*for (User user : users.values()) {
            if (user.getEmail() != null && user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;*/
    }

    public User getByName(String name) {
        return users.entrySet().stream()
                .filter(e -> e.getValue().getFirstName() != null)
                .filter(e -> e.getValue().getFirstName().equalsIgnoreCase(name))
                .findFirst()
                .get()
                .getValue();
    }

    public Set<Ticket> getBookedTickets(User user) {
        User byId = getById(user.getId());
        return byId.getBookedTickets();
    }

    private Gender getGender(String gender) {
        for (Gender temporaryGender : Gender.values()) {
            if (gender.equalsIgnoreCase(temporaryGender.toString())) {
                return temporaryGender;
            }
        }
        return Gender.UNSPECIFIED;
    }

    private Calendar toDateFormat(String date) {
        Calendar birthday = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            sdf.getCalendar().setTime(sdf.parse(date));
            birthday = sdf.getCalendar();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthday;
    }


    public static void main(String[] args) {
        UserService userService = new UserService();
        User byEmail = userService.getByEmail("grendjer@gmail.com");
        System.out.println(byEmail.getFirstName());

        User byId = userService.getById(1);
        System.out.println(byId.getFirstName());

        System.out.println(userService.getByName("ron").getFirstName());

        System.out.println(userService.getBookedTickets(DatabaseMock.getUsers().get(3)));

        User user = new User("Severun", "Snoup", Gender.MALE);
        user.setId(5);
        userService.register(user);
        System.out.println(DatabaseMock.getUsers().get(5).getFirstName());
    }
}
