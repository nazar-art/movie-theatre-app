package net.lelyak.edu.service;

import net.lelyak.edu.entity.User;
import net.lelyak.edu.entity.UserAccount;
import net.lelyak.edu.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount getForUser(User user) {
        return userAccountRepository.getDao().getForUser(user);
    }

    public long register(UserAccount account) {
        return userAccountRepository.put(account);
    }

    public void update(UserAccount account) {
        userAccountRepository.getDao().update(account);
    }

    public void delete(UserAccount entity) {
        userAccountRepository.getDao().delete(entity);
    }

}
