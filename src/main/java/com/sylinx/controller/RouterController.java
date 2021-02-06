package com.sylinx.controller;

import com.sylinx.filter.TokenFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RouterController {

    @RequestMapping({"/", "/index"})
    public String index(HttpServletRequest request) {
        String token = TokenFilter.getToken(request);
        if(StringUtils.isBlank(token)) {
            return "login";
        }
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }
}
