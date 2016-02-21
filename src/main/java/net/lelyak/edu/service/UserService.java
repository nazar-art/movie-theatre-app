package net.lelyak.edu.service;

import net.lelyak.edu.dao.impl.UserDaoImpl;
import net.lelyak.edu.dao.mock.DatabaseMock;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.Logger;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserDaoImpl userDao;
    private Map<Integer, User> users;

    public UserService() {
        users = DatabaseMock.getUsers();
        users.put(1, new User(1, "Garry Potter"));
        users.put(2, new User(2, "Ron Weasley"));
        users.put(3, new User(3, "Germiona Grendjer", "grendjer@gmail.com",
                new HashSet<Ticket>(Collections.singletonList(
                        new Ticket(DatabaseMock.getEvents().get(1), 30d, users.get(3))))));
    }

    public Integer register(User user) {
//        return users.put(user.getId(), user);
        return userDao.create(user);
    }

    public Integer register(User user, String userBirthday) {
        Validate.notNull(user, "user can not be null for registration");
        Validate.notNull(userBirthday, "userBirthday can not be null");

        user.setRole("user");
        user.setBirthday(toDateFormat(userBirthday));
        return register(user);
    }

    public void remove(User user) {
//        users.remove(user.getId());
        userDao.delete(user.getId());
    }

    public User getById(int id) {
//        return users.get(id);
        return userDao.read(id);
    }

    public User getByEmail(String email) {
        Logger.info("Looking for the user with mail: " + email);
        /*return users.values().stream()
                .filter(e -> e.getEmail() != null)
                .filter(e -> e.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .get();*/
        return userDao.getByEmail(email);
    }

    public User getByName(String name) {
        Logger.info("Looking for the user with name: " + name);
        /*return users.values().stream()
                .filter(e -> e.getName() != null)
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst()
                .get();*/
        return userDao.getByName(name);
    }

    public int getTotalUsersCount() {
        return userDao.getTotalCount();
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
