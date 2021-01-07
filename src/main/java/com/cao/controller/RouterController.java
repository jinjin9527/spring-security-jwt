package com.cao.controller;

import com.cao.model.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
//        System.out.println(inMemoryUserDetailsManager.loadUserByUsername("user"));
        System.out.println(((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getToken());
        return "user/" + id;
    }

    @RequestMapping("/admin/{id}")
    public String admin(@PathVariable("id") int id) {
//        System.out.println(inMemoryUserDetailsManager.loadUserByUsername("admin"));
        System.out.println(((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getToken());
        return "admin/" + id;
    }

    @RequestMapping("/guest/{id}")
    public String guest(@PathVariable("id") int id) {
//        System.out.println(inMemoryUserDetailsManager.loadUserByUsername("guest"));
        System.out.println(((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getToken());
        return "guest/" + id;
    }

}
