package com.cao.model;

import java.io.Serializable;


public class Token implements Serializable {

	private static final long serialVersionUID = 6314027741784310221L;

	private String token;

	private Long loginTime;

	public Token(String token, Long loginTime) {
		super();
		this.token = token;
		this.loginTime = loginTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}
}
