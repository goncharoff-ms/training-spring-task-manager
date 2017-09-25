package ru.alastor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 25.09.17.
 *
 * @author Maxim Goncharov
 */
@Controller
public class MainController {

    @RequestMapping(path = {"/", "/index"})
    public String index() {
        return "index";
    }
}
