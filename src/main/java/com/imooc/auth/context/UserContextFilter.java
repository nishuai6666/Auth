package com.imooc.auth.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class UserContextFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest  request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		ResponseContext.setCurrent(response);
		
		if (request.getRequestURI().contains("login")) {
			Cookie cookie = new Cookie("auth",null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		if (request.getRequestURI().contains("index.jsp")) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		if (request.getRequestURI().endsWith(".css")||request.getRequestURI().endsWith("index.jsp")
			||request.getRequestURI().endsWith(".jpg")||request.getRequestURI().endsWith(".gif")) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		String cookieValue = "";
		if (null!=request.getCookies()) {
			
		}
		
	}



}
