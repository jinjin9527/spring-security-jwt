package com.sylinx.controller;

import com.sylinx.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RouterController {

    @Autowired
    TokenService tokenService;

    @RequestMapping({"/", "/index"})
    public String index(HttpServletRequest request) throws Exception {
        String token = tokenService.getToken(request);

        if(StringUtils.isBlank(token) || !tokenService.checkToken(token)) {
            return "login";
        }
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }
}
