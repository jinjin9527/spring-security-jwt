package com.sylinx.service.impl;


import com.sylinx.model.LoginUser;
import com.sylinx.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	private static final Map<String ,LoginUser> userMap = new HashMap<>();

	public UserServiceImpl(){
		UserDetails adminUserDetails = User.withUsername("admin").password(new BCryptPasswordEncoder().encode("123123")).roles("admin").build();
        UserDetails userUserDetails = User.withUsername("user")
                .password(new BCryptPasswordEncoder().encode("123123")).roles("user").build();
        UserDetails guestUserDetails = User.withUsername("guest")
                .password(new BCryptPasswordEncoder().encode("123123")).roles("guest").build();

		LoginUser admin = new LoginUser(adminUserDetails, "adminToken", 1L, 1L);
        LoginUser user = new LoginUser(userUserDetails,"userToken", 1L, 1L);
        LoginUser guest = new LoginUser(guestUserDetails,"guestToken", 1L, 1L);
		userMap.put("admin", admin);
		userMap.put("user", user);
		userMap.put("guest", guest);
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public LoginUser getUser(String username) {
		return userMap.get(username);
	}


}
