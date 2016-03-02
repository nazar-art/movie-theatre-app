package net.lelyak.edu.service;

import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.repository.UserRepository;
import net.lelyak.edu.utils.Logger;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * UserService - Manages registered users
 * register, remove, getById, getUserByEmail, getUsersByName, getBookedTickets
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketService ticketService;

    public void register(User user) {
        userRepository.put(user);
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
        userRepository.remove(user);
    }

    public User getById(long id) {
        return userRepository.getById(id);
    }

    public User getByEmail(String email) {
        Logger.info("Looking for the user with mail: " + email);
        return userRepository.getUserByEmail(email);
    }

    public User getByName(String name) {
        Logger.info("Looking for the user with name: " + name);
        return userRepository.getByName(name);
    }

    public int getTotalUsersCount() {
        return userRepository.getTotalCount();
    }

    public List<Ticket> getBookedTickets(User user) {
        return ticketService.getTicketsForUser(user);
    }
}
