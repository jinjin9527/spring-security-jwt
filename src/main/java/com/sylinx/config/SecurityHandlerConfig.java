package com.sylinx.config;

import com.sylinx.model.LoginUser;
import com.sylinx.model.ResponseInfo;
import com.sylinx.service.TokenService;
import com.sylinx.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityHandlerConfig {

	@Autowired
	TokenService tokenService;

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				LoginUser loginUser = (LoginUser) authentication.getPrincipal();
				String tokenString = tokenService.createToken(loginUser.getUsername(), loginUser.getPassword());
				System.out.println("createToken : " + tokenString);
				ResponseUtil.responseJson(response, HttpStatus.OK.value(), tokenString);
			}
		};
	}

	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				exception.printStackTrace();
				String msg = "ユーザー、または、パスワードが不正！";;
				ResponseInfo info = new ResponseInfo(HttpStatus.UNAUTHORIZED.value() + "", msg);
				ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), info);
			}
		};

	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				ResponseInfo info = new ResponseInfo(HttpStatus.UNAUTHORIZED.value() + "", "ログインしてください");
				ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), info);
			}
		};
	}

	@Bean
	public LogoutSuccessHandler logoutSussHandler() {
		return new LogoutSuccessHandler() {

			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				ResponseInfo info = new ResponseInfo(HttpStatus.OK.value() + "", "ログアウト完了");
				tokenService.deleteToken(request);
				ResponseUtil.responseJson(response, HttpStatus.OK.value(), info);
			}
		};
	}
}
