package com.cao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "views/login";
    }

    @RequestMapping("/user/{id}")
    public String user(@PathVariable("id") int id) {
        return "views/user/" + id;
    }

    @RequestMapping("/admin/{id}")
    public String admin(@PathVariable("id") int id) {
        return "views/admin/" + id;
    }

    @RequestMapping("/guest/{id}")
    public String guest(@PathVariable("id") int id) {
        return "views/guest/" + id;
    }

}
