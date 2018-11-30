package com.imooc.auth.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.auth.dao.UserDao;
import com.imooc.auth.dao.UserRoleDao;
import com.imooc.auth.entity.User;
import com.imooc.auth.entity.UserRole;
import com.imooc.auth.service.impl.BaseUserService;

public class UserService implements BaseUserService{
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public void deleteUserById(Long id) {
		userDao.deleteById(id);
	}

	@Override
	public User getUser(String name, String pwd) {
		return userDao.findUser(name, pwd);
	}

	@Override
	public Collection<User> getUsers(int page, int size) {
		return userDao.findUsers(page, size);
	}

	@Override
	public Collection<User> getUsers(Collection<Long> ids) {
		return userDao.findByIds(ids);
	}

	@Override
	public List<UserRole> getUserRoles(int page,int size) {
		return userRoleDao.findUserRoles(page, size);
	}

	@Override
	public List<UserRole> getUserRolesByUserId(Long userId) {
		return userRoleDao.findUserRoleByUserId(userId);
	}

	@Override
	public void addUserRoles(Long userId, Long[] roleIds) {
		List<UserRole> userRoles = new ArrayList<>();
		
		Arrays.asList(roleIds).forEach((roleId) ->{
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRoles.add(userRole);
		});
		userRoleDao.saveUserRoles(userRoles);
		
	}



	
	
}
