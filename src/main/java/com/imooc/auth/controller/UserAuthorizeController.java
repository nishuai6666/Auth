package com.imooc.auth.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.auth.common.AjaxResult;
import com.imooc.auth.common.BaseEntity;
import com.imooc.auth.dto.Authorize;
import com.imooc.auth.entity.Role;
import com.imooc.auth.entity.User;
import com.imooc.auth.entity.UserRole;
import com.imooc.auth.service.RoleService;
import com.imooc.auth.service.UserService;



@RequestMapping("/authorize")
@Controller
public class UserAuthorizeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 授权首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index(){
		return"/security/authorize/authorize_list";
	}
	
	/**
	 * 用户角色首页
	 * @return
	 */
	public String authorizeIndex(){
		return"/security/authorize/user_role_list";
	}
	
	/**
	 * 查询授权信息
	 * @param page 当前页码
	 * @param rows 每页记录数
	 * @return 权限集合
	 */
	@RequestMapping(value="/getAuthorizes",method = RequestMethod.POST)
	@ResponseBody
	public List<Authorize> getAuthorizes(Integer page,Integer rows){
		List<UserRole> userRoles = userService.getUserRoles(page, rows);
		Collection<Long> userIds = new HashSet<>();
		Collection<Long> roleIds = new HashSet<>();
		userRoles.forEach((ur)->{
			userIds.add(ur.getId());
			roleIds.add(ur.getRoleId());
		});
		
		Collection<User> users = userService.getUsers(userIds);
		List<Role> roles = roleService.getRoles(roleIds);
		
		Map<Long,User> userMap = BaseEntity.idEntityMap(users);
		Map<Long, Role> roleMap = BaseEntity.idEntityMap(roles);
		
		List<Authorize> authorizes = new LinkedList<>();
		userRoles.forEach((ur)->{
			Authorize authorize = new Authorize();
			authorize.setUserRoleId(ur.getId());
			authorize.setUserId(ur.getUserId());
			authorize.setUserName(userMap.get(ur.getUserId()).getName());
			authorize.setRoleId(ur.getRoleId());
			authorize.setRoleName(roleMap.get(ur.getRoleId()).getName());;
			authorizes.add(authorize);
		});
		return authorizes;
	}
	
	
	/**
	 * 根据用户的Id查询用户角色对应关系
	 * @param userId 用户ID
	 * @return 用户角色对应关系集合
	 */
	@RequestMapping(value = "/getUserRoleByUserId",method = RequestMethod.POST)
	@ResponseBody
	public List<UserRole> getUserRoleByUserId(Long userId){
		return userService.getUserRolesByUserId(userId);
	}
	
	/**
	 * 设置权限
	 * @param user
	 * @param roleIds
	 * @return
	 */
	public AjaxResult setAuthorize(User user,String roleIds)
	{
		String[] temp = roleIds.split(",");
		Long[] roleIdArray  = new Long[temp.length];
		for(int i=0;i<roleIdArray.length;i++){
			roleIdArray[i] = Long.valueOf(temp[i]);
		}
		userService.addUserRoles(user.getId(), roleIdArray);
		return AjaxResult.success();
	}
}
