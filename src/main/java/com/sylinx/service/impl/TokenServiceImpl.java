package com.sylinx.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sylinx.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    public static final String TOKEN_KEY = "token";

    @Autowired
    WebClient webClient;

    public boolean checkToken(String token) throws JsonProcessingException {
        String service = "/checkToken";
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("token", token);
        String result = webClient.post().uri(service).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(String.class).block();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(result, HashMap.class);
        if (!(Boolean)map.get("result")) {
            return false;
        }
        return true;
    }

    public String createToken(String username, String password){
        String service = "/createToken";
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", username);
        formData.add("password", password);
        String token = webClient.post().uri(service).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(String.class).block();
        System.out.println("createToken : " + token);
        return token;
    }

    public void deleteToken(HttpServletRequest request){
        String service = "/deleteToken";
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        String token = this.getToken(request);
        formData.add("token", token);
        System.out.println("logout : " + token);
        webClient.post().uri(service).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(String.class).block();
    }


    public String getToken(HttpServletRequest request) {
        String token = request.getParameter(TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            token = request.getHeader(TOKEN_KEY);
        }
        return token;
    }
}
