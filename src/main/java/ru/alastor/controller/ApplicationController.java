package ru.alastor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alastor.dao.ApplicationDao;
import ru.alastor.dao.UserDao;
import ru.alastor.domain.Application;
import ru.alastor.domain.builder.ApplicationBuilder;
import ru.alastor.service.AuthTokenService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 23.09.17.
 *
 * @author Maxim Goncharov
 */

@RestController
@CrossOrigin
public class ApplicationController {

    private final ApplicationDao applicationDao;
    private final UserDao userDao;

    private final AuthTokenService authTokenService;

    @Autowired
    public ApplicationController(ApplicationDao applicationDao, UserDao userDao, AuthTokenService authTokenService) {
        this.applicationDao = applicationDao;
        this.authTokenService = authTokenService;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/applications", method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam("token") String token,
                                 @RequestParam("userLogin") String userLogin) {

        if (!authTokenService.checkAuthTokenByLogin(userLogin, token)) {
            return new ResponseEntity<>("error token or login", HttpStatus.ACCEPTED);
        }

        List<Application> applicationsList = new ArrayList<>();
        for (Application application : applicationDao.findAll()) {
            applicationsList.add(application);
        }
        return new ResponseEntity<>(applicationsList, HttpStatus.OK);
    }

    @RequestMapping(path = "/applications/{appId}", method = RequestMethod.GET)
    public Application getAppById(@PathVariable(name = "appId") Long appId) throws JsonProcessingException {
        return applicationDao.findOne(appId);
    }

    @RequestMapping(path = "/new/application", method = RequestMethod.POST)
    public ResponseEntity newApplication(@RequestParam("name") String name,
                               @RequestParam("info") String info,
                               @RequestParam("order") String order,
                               @RequestParam("date") String date,
                               @RequestParam("token") String token,
                               @RequestParam("userLogin") String userLogin
                               ) {
        if (!authTokenService.checkAuthTokenByLogin(userLogin, token)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = ApplicationBuilder.getInstance()
                .createApplication()
                .setName(name)
                .setInfo(info)
                .setDateReview(date)
                .setOrder(order)
                .setUserId(userDao.findByLogin(userLogin).getId())
                .getApplication();

        applicationDao.save(application);

        return new ResponseEntity<>(String.valueOf(application),HttpStatus.OK);
    }



}
