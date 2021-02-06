package com.sylinx.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sylinx.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	private static final Map<String ,LoginUser> userMap = new HashMap<>();

	@Autowired
	private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8082/").build();

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) {
		LoginUser loginUser = null;
		String service = "/getUser";
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("userName", username);

		String result = webClient.post().uri(service).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(String.class).block();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> map = mapper.readValue(result, HashMap.class);
			if ((Boolean)map.get("result")) {
				UserDetails userDetails = User.withUsername(username).password(new BCryptPasswordEncoder().encode((String) map.get("password"))).roles("admin").build();
				loginUser = new LoginUser(userDetails, "adminToken", 1L, 1L);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return loginUser;
	}
}
