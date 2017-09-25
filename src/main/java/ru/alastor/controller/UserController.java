package ru.alastor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.alastor.dao.UserDao;
import ru.alastor.domain.Application;
import ru.alastor.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 24.09.17.
 *
 * @author Maxim Goncharov
 */
@Controller
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @ResponseBody
    @RequestMapping(path = "/users")
    public String allUsers() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> userList = new ArrayList<>();
        for (User user : userDao.findAll()) {
            userList.add(user);
        }
        return objectMapper.writeValueAsString(userList);
    }

}
