package net.lelyak.edu.service;

import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.repository.EventRepository;
import net.lelyak.edu.repository.UserRepository;
import net.lelyak.edu.utils.Logger;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * UserService - Manages registered users
 * register, remove, getById, getUserByEmail, getUsersByName, getBookedTickets
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TicketService ticketService;

    private Map<Integer, User> users;

    public void register(User user) {
//        return users.put(user.getId(), user);
        userRepository.put(user);
//        register(user.getName(), user.getEmail(), user.getBirthday());
    }

    public void register(User user, Date userBirthday) {
        Validate.notNull(user, "user can not be null for registration");
        Validate.notNull(userBirthday, "userBirthday can not be null");

        user.setRole("user");
        user.setBirthday(userBirthday);
        register(user);
    }

    public User signIn(String name) {
        return userRepository.getByName(name);
    }

    public User register(String name, String email, Date birthday) {
        User user = signIn(name);

        if (user == null) {
            user = new User();
            user.setBirthday(birthday);
            user.setEmail(email);
            user.setName(name);
            user.setRole("user");
            userRepository.put(user);
        }
        return signIn(name);
    }

    public void remove(User user) {
//        users.remove(user.getId());
//        userDao.delete(user);
        userRepository.remove(user);
    }

    public User getById(long id) {
//        return users.get(id);
//        return userDao.getById(id);
        return userRepository.getById(id);
    }

    public User getByEmail(String email) {
        Logger.info("Looking for the user with mail: " + email);
        /*return users.values().stream()
                .filter(e -> e.getEmail() != null)
                .filter(e -> e.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .get();*/
//        return userDao.getByEmail(email);
        return userRepository.getUserByEmail(email);
    }

    public User getByName(String name) {
        Logger.info("Looking for the user with name: " + name);
        /*return users.values().stream()
                .filter(e -> e.getName() != null)
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst()
                .get();*/
//        return userDao.getByName(name);
        return userRepository.getByName(name);
    }

    public int getTotalUsersCount() {
//        return userDao.getTotalCount();
        return userRepository.getTotalCount();
    }

    public List<Ticket> getBookedTickets(User user) {
        return ticketService.getTicketsForUser(user);
    }
}
