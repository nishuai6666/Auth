package com.imooc.auth.service.impl;

import java.util.Collection;
import java.util.List;

import com.imooc.auth.entity.Role;
import com.imooc.auth.entity.RoleFunction;
import com.imooc.auth.entity.User;
import com.imooc.auth.entity.UserRole;

public interface BaseUserRoleService {
	
	/**
	 * 添加权限
	 * @param role,roleFunction
	 */
	
	void addRole(Role role, Collection<RoleFunction> roleFunction);
	
	
	/**
	 * 更改权限
	 * @param role,roleFunction
	 */
	
	void eidtRole(Role role, Collection<RoleFunction> roleFunction);
	
	/**
	 * 删除用户
	 * @param roleId
	 */
	
	void deleteRole(Long roleId);
	
	/**
	 * 查询用户(登录)
	 * @param name,pwd
	 */
	List<Role> getRole(int page,int size);
	
	
	
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
