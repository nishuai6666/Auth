package com.imooc.auth.service.impl;

import java.util.Collection;
import java.util.List;

import com.imooc.auth.entity.User;
import com.imooc.auth.entity.UserRole;

public interface BaseUserService {
	
	/**
	 * 添加用户
	 * @param user
	 */
	
	void addUser(User user);
	
	
	/**
	 * 更改用户
	 * @param user
	 */
	
	void updateUser(User user);
	
	/**
	 * 删除用户
	 * @param id
	 */
	
	void deleteUserById(Long id);
	
	/**
	 * 查询用户(登录)
	 * @param name,pwd
	 */
	User getUser(String name,String pwd);
	
	
	
	/**
	 * 查询所有用户（分页）
	 * @param page,size
	 */
	Collection<User> getUsers(int page,int size);
	
	
	/**
	 * 查询所有用户（ID集合）
	 * @param page,size
	 */
	Collection<User> getUsers(Collection<Long> ids);
	
	
	/**
	 * 查询用户角色关系对应（分页）
	 * @param page,size
	 */
	List<UserRole> getUserRoles(int page,int size);
	
	
	/**
	 * 查询用户角色对应关系
	 * @param userId
	 */
	List<UserRole> getUserRolesByUserId(Long userId);
	
	/**
	 * 保存用户角色对应关系
	 * @param userId,roleIds[]
	 */
	void addUserRoles(Long userId, Long[] roleIds);
	
	
	
}
