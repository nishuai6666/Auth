package com.imooc.auth.context;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.imooc.auth.dto.Accordion;
import com.imooc.auth.entity.User;


public class LoginUserCache {
	
/*	private static Map<Long,LoginUser> cache = new HashMap<Long,LoginUser>();
	
//	public static User get(){
//		
//	}
	
	*//**
	 * 
	 * @param user
	 * @param expire 单位秒，如果是30分钟过期，即：60*30=1800
	 *//*
	public static void put(User user,long expire){
		long expireTime = Calendar.getInstance().getTime().getTime() + expire*1000;
		LoginUser loginUser = new LoginUser();
		loginUser.setUser(user);
		loginUser.setExpire(expireTime);;
		cache.put(user.getId(), loginUser);
	}
	
	public static void remove(){
		
	}
	
	private static class LoginUser{
		private long expire;
		private User user;
		public long getExpire() {
			return expire;
		}
		public void setExpire(long expire) {
			this.expire = expire;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
	}*/
	
	
	private static Map<String,User> cache = new HashMap<String, User>();
	
	private static Map<String,List<Accordion>> userAccordionMap = new HashMap<String, List<Accordion>>();
	
	public static void put(User user){
		cache.put(user.getName(), user);
		UserContext.setCurrent(user);
		setCookie(user);
	}
	
	public static User get(String username){
	 return cache.get(username);
	}
	
	public static void setCookie(User user){
		int expire = 1800;//秒
		String source = user.getName() +"$"+user.getPwd();
		byte[] result = Base64.getEncoder().encode(source.getBytes());
		Cookie cookie = new Cookie("auth",new String(result));
		cookie.setMaxAge(expire);
		ResponseContext.getCurrent().addCookie(cookie);
	}
	
	public static void remove(String username){
		cache.remove(username);
		Cookie cookie = new Cookie("auth",null);
		ResponseContext.getCurrent().addCookie(cookie);
		UserContext.setCurrent(null);
	}
	
	public static void setAccordions(String username,List<Accordion> accordions){
		userAccordionMap.put(username, accordions);
	}
	
	public static void getAccordions(String username){
		userAccordionMap.get(username);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
