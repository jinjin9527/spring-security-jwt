package com.sylinx.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    public boolean checkToken(String token) throws JsonProcessingException;
    public String getToken(HttpServletRequest request);
    public void deleteToken(HttpServletRequest request);
    public String createToken(String username);

}
