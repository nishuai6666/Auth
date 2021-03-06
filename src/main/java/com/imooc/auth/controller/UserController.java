package com.imooc.auth.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.auth.common.AjaxResult;
import com.imooc.auth.entity.User;
import com.imooc.auth.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	/**
	 * 用户首页
	 * @return
	 */
	@RequestMapping(value="/index")
	public String userList(){
		return "/security/user/user_list";
	}
	
	/**
	 * 新建,修改用户信息
	 * @param user 用户信息
	 * @return 操作结果
	 */
	@RequestMapping(value="/addEditUser",method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addEditUser(User user){
		if (null == user.getId()) {
			userService.addUser(user);
		}else{
			userService.updateUser(user);
		}
		return AjaxResult.success();
	}
	
	/**
	 * 根据用户ID删除用户
	 * @param 用户ID
	 * @return操作结果
	 */
	@RequestMapping(value="/deleteUser",method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult deleteUser(Long id){
		userService.deleteUserById(id);
		return AjaxResult.success();
	}
	
	/**
	 * 分页查询用户信息
	 * @param page 当前页面
	 * @param rows 每页记录数
	 * @return 用户信息结合
	 */
	@RequestMapping(value="/getUsers",method = RequestMethod.POST)
	@ResponseBody
	public Collection<User> getUser(Integer page,Integer rows){
		return userService.getUsers(page, rows);
	}
	
	
	
}
