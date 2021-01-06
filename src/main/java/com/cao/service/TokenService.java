package com.cao.service;

import com.cao.model.LoginUser;
import com.cao.model.Token;

public interface TokenService {

	Token saveToken(LoginUser loginUser);

	void refresh(LoginUser loginUser);

	LoginUser getLoginUser(String token);

	boolean deleteToken(String token);

}
