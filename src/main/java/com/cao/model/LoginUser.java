package com.cao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class LoginUser implements UserDetails, Serializable {

	private static final long serialVersionUID = -1379274258881257107L;

	private String token;
	private Long loginTime;
	private Long expireTime;
	private String password;
	private String username;
	private Collection<GrantedAuthority> authorities;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;

	public LoginUser(UserDetails user, String token, Long loginTime, Long expireTime) {
		this.token = token;
		this.loginTime = loginTime;
		this.expireTime = expireTime;
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities =  ((User) user).getAuthorities();
		this.accountNonExpired = user.isAccountNonExpired();
		this.accountNonLocked = user.isAccountNonLocked();
		this.credentialsNonExpired = user.isCredentialsNonExpired();
		this.enabled = user.isEnabled();
	}

	public LoginUser(){
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
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {return this.username;}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {return this.accountNonExpired;}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
