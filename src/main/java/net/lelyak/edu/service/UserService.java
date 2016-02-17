package net.lelyak.edu.service;

import net.lelyak.edu.dao.mock.DatabaseMock;
import net.lelyak.edu.entity.Role;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.CommonIndexes;
import net.lelyak.edu.utils.Logger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * UserService - Manages registered users
 * register, remove, getById, getUserByEmail, getUsersByName, getBookedTickets
 */
@Service
public class UserService {
    private final int USER_INDEX = CommonIndexes.TWO.getIndex();
    private final int ADMIN_INDEX = CommonIndexes.ONE.getIndex();

    private Map<Integer, User> users;

    public UserService() {
        users = DatabaseMock.getUsers();
        users.put(1, new User(1, "Garry Potter"));
        users.put(2, new User(2, "Ron Weasley"));
        users.put(3, new User(3, "Germiona Grendjer", "grendjer@gmail.com",
                new HashSet<Ticket>(Collections.singletonList(
                        new Ticket(DatabaseMock.getEvents().get(1), 30d, users.get(3))))));
    }

    public User register(User user) {
        return users.put(user.getId(), user);
    }

    public User register(User user, String date) {
        user.setBirthday(toDateFormat(date));
        Role registeredUser = new Role();
        registeredUser.setId(USER_INDEX);
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
        Logger.info("Looking for the user with mail: " + email);
        return users.values().stream()
                .filter(e -> e.getEmail() != null)
                .filter(e -> e.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .get();
        /*for (User user : users.values()) {
            if (user.getEmail() != null && user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;*/
    }

    public User getByName(String name) {
        Logger.info("Looking for the user with name: " + name);
        return users.values().stream()
                .filter(e -> e.getName() != null)
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst()
                .get();
    }

    public Set<Ticket> getBookedTickets(User user) {
        User byId = getById(user.getId());
        return byId.getBookedTickets();
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
}
