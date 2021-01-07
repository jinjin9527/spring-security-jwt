package com.sylinx.controller;

import com.sylinx.model.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

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
        return "login";
    }

    @RequestMapping("/user/{id}")
    public String user(@PathVariable("id") int id) {
        return "user/" + id;
    }

    @RequestMapping("/admin/{id}")
    public String admin(@PathVariable("id") int id) {
        return "admin/" + id;
    }

    @RequestMapping("/guest/{id}")
    public String guest(@PathVariable("id") int id) {
        return "guest/" + id;
    }

}
