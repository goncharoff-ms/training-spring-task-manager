package ru.alastor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.alastor.dao.ApplicationDao;
import ru.alastor.domain.Application;

import java.util.*;

/**
 * Created on 23.09.17.
 *
 * @author Maxim Goncharov
 */

@Controller
@CrossOrigin
public class ApplicationController {

    private final ApplicationDao applicationDao;

    @Autowired
    public ApplicationController(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }
    @ResponseBody
    @RequestMapping(path = "/applications", method = RequestMethod.GET)
    public String getAll() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Application> applicationsList = new ArrayList<>();
        for (Application application : applicationDao.findAll()) {
            applicationsList.add(application);
        }
        return objectMapper.writeValueAsString(applicationsList);
    }

    @ResponseBody
    @RequestMapping(path = "/application/{appId}", method = RequestMethod.GET)
    public String getAppById(@PathVariable(name = "appId") Long appId) throws JsonProcessingException {
        return new ObjectMapper().
                writeValueAsString(applicationDao.findOne(appId));
    }



}
