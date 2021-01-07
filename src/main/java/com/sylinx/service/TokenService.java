package com.sylinx.service;

import com.sylinx.model.LoginUser;
import com.sylinx.model.Token;

public interface TokenService {

	Token saveToken(LoginUser loginUser);

	void refresh(LoginUser loginUser);

	LoginUser getLoginUser(String token);

	boolean deleteToken(String token);

}
