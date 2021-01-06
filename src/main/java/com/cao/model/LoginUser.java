package com.cao.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
public class LoginUser implements UserDetails {

	private static final long serialVersionUID = -1379274258881257107L;

	private String token;
	private Long loginTime;
	private Long expireTime;
	private UserDetails user;

	public LoginUser(UserDetails user, String token, Long loginTime, Long expireTime) {
		this.user = user;
		this.token = token;
		this.loginTime = loginTime;
		this.expireTime = expireTime;
	}
	public LoginUser(){

	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.user.getAuthorities();
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.user.isEnabled();
	}
}
