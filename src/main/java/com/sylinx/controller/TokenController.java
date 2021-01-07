package com.sylinx.controller;

import com.sylinx.filter.TokenFilter;
import com.sylinx.model.LoginUser;
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

    @PostMapping({"/tokencheck"})
    public ResponseInfo token(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(TokenFilter.getToken(request));
        ResponseInfo responseInfo = new ResponseInfo();
        if (loginUser == null) {
            responseInfo.setCode(HttpStatus.BAD_REQUEST.name());
            responseInfo.setMessage("トークンError");
        } else {
            responseInfo.setCode(HttpStatus.OK.name());
            responseInfo.setMessage("トークンOK");
        }
        return responseInfo;
    }
}
