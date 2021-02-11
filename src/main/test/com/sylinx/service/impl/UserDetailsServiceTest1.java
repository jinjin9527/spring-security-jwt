package com.sylinx.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest1 {

    UserDetailsServiceImpl userDetailsService;

    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    private WebClient.RequestBodySpec requestBodySpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;
    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @BeforeEach
    void setUp() {

        userDetailsService = new UserDetailsServiceImpl();
        ReflectionTestUtils.setField(userDetailsService, "webClient", webClientMock);
    }

    @Test
    public void loadUserByUsername_test1() {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        resultMap.put("password", "123");
        JSONObject json =  new JSONObject(resultMap);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("userName", "admin");
        lenient().when(webClientMock.post()).thenReturn(requestBodyUriSpec);
        lenient().when(requestBodyUriSpec.uri("/getUser")).thenReturn(requestBodySpec);
        lenient().when(requestBodySpec.contentType(any())).thenReturn(requestBodyUriSpec);
        lenient().when(requestBodyUriSpec.body(any())).thenReturn(requestHeadersSpecMock);
        lenient().when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        lenient().when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(json.toJSONString()));

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
            boolean result = (new BCryptPasswordEncoder()).matches("123", userDetails.getPassword());
            assertTrue(result);
        }catch(Exception e) {
            e.printStackTrace();
        }


    }

}
