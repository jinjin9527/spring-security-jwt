package com.sylinx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sylinx.filter.TokenFilter;
import com.sylinx.model.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {

    @Autowired
    WebClient webClient;

    @PostMapping({"/checkToken"})
    public ResponseInfo token(HttpServletRequest request) throws JsonProcessingException {
        String token = TokenFilter.getToken(request);
        String service = "/checkToken";
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("token", token);

        String result = webClient.post().uri(service).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(String.class).block();
        System.out.println("checkToken : " + token + " - " + result);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(result, HashMap.class);
        ResponseInfo info = new ResponseInfo(HttpStatus.OK.value() + "", "success");
        if (!(Boolean)map.get("result")) {
            info.setCode(HttpStatus.UNAUTHORIZED.value() + "");
            info.setMessage("failure");
        }
        return info;
    }
}
