package net.lelyak.edu.controller;

import net.lelyak.edu.entity.User;
import net.lelyak.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Date;


@RestController
public class SignUpController {

    @Autowired
    private UserService userService;  //Service which will do all data retrieval/manipulation work

    private PasswordEncoder passwordEncoder ;

    @PostConstruct
    public void init(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {

        if (userService.isUserExist(user)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(null); // TODO get userRoles from request
        user.setBirthday(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.register(user);

        if (userService.isUserExist(user)) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
