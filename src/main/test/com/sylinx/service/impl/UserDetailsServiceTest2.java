package com.sylinx.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDetailsServiceTest2 {

    public static MockWebServer mockBackEnd;
    private UserDetailsServiceImpl userDetailsService;
    private ObjectMapper MAPPER = new ObjectMapper();

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {

        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        WebClient webClient = WebClient.create(baseUrl);
        userDetailsService = new UserDetailsServiceImpl();
        ReflectionTestUtils.setField(userDetailsService, "webClient", webClient);
    }

    @Test
    public void loadUserByUsername_test1() throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        resultMap.put("password", "123");
        mockBackEnd.enqueue(new MockResponse().setBody(MAPPER.writeValueAsString(resultMap))
                .addHeader("Content-Type", "application/json"));

        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
        boolean result = (new BCryptPasswordEncoder()).matches("123", userDetails.getPassword());
        assertTrue(result);
    }
}