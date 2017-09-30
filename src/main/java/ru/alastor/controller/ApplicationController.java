package ru.alastor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alastor.dao.ApplicationDao;
import ru.alastor.domain.Application;

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

    @Autowired
    public ApplicationController(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @RequestMapping(path = "/applications", method = RequestMethod.GET)
    public List<Application> getAll() throws JsonProcessingException {
        List<Application> applicationsList = new ArrayList<>();
        for (Application application : applicationDao.findAll()) {
            applicationsList.add(application);
        }
        return applicationsList;
    }

    @RequestMapping(path = "/application/{appId}", method = RequestMethod.GET)
    public Application getAppById(@PathVariable(name = "appId") Long appId) throws JsonProcessingException {
        return applicationDao.findOne(appId);
    }



}
