package com.sylinx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sylinx.model.ResponseInfo;
import com.sylinx.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TokenController {

    @Autowired
    TokenService tokenService;

    @PostMapping({"/checkToken"})
    public ResponseInfo checkToken(HttpServletRequest request) throws JsonProcessingException {

        String token = tokenService.getToken(request);
        boolean checkResult = tokenService.checkToken(token);
        ResponseInfo info = new ResponseInfo(HttpStatus.OK.value() + "", "success");
        if (!checkResult) {
            info.setCode(HttpStatus.UNAUTHORIZED.value() + "");
            info.setMessage("failure");
        }
        return info;
    }
}
