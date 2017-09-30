package ru.alastor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alastor.dao.UserDao;
import ru.alastor.domain.User;
import ru.alastor.security.auth.JwtAuthenticationToken;
import ru.alastor.security.model.UserContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 24.09.17.
 *
 * @author Maxim Goncharov
 */
@RestController
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value="/me", method= RequestMethod.GET)
    public @ResponseBody
    UserContext get(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }

    @RequestMapping(path = "/users")
    public List<User> allUsers() throws JsonProcessingException {
        List<User> userList = new ArrayList<>();
        for (User user : userDao.findAll()) {
            userList.add(user);
        }
        return userList;
    }

}
