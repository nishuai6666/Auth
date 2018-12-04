package com.imooc.auth.context;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.imooc.auth.entity.User;


public class LoginUserCache {
	
	private static Map<Long,LoginUser> cache = new HashMap<Long,LoginUser>();
	
//	public static User get(){
//		
//	}
	
	/**
	 * 
	 * @param user
	 * @param expire 单位秒，如果是30分钟过期，即：60*30=1800
	 */
	public static void put(User user,long expire){
		long expireTime = Calendar.getInstance().getTime().getTime() + expire*1000;
		LoginUser loginUser = new LoginUser();
		loginUser.setUser(user);
		loginUser.setExpire(expireTime);;
		cache.put(user.getId(), loginUser);
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
	}
	
	
}
