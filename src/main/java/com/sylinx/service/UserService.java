package com.sylinx.service;

import com.sylinx.model.LoginUser;

public interface UserService {

	LoginUser getUser(String username);
}
