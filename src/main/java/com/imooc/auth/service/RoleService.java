package com.imooc.auth.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.auth.dao.RoleDao;
import com.imooc.auth.dao.RoleFunctionDao;
import com.imooc.auth.entity.Role;
import com.imooc.auth.entity.RoleFunction;
import com.imooc.auth.entity.User;
import com.imooc.auth.entity.UserRole;
import com.imooc.auth.service.impl.BaseUserRoleService;

@Service
public class RoleService implements BaseUserRoleService {
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleFunctionDao roleFunctionDao;
	
	
	@Override
	public void addRole(Role role, Collection<RoleFunction> roleFunction) {
		roleDao.saveRole(role);
		roleFunction.forEach((rf) -> rf.setRoleId(role.getId()));
		roleFunctionDao.saveRoleFunctions(roleFunction);
	}

	@Override
	public void eidtRole(Role role, Collection<RoleFunction> roleFunction) {
		roleDao.updateRole(role);
		roleFunctionDao.deleteByRoleId(role.getId());
		roleFunction.forEach((rf) ->rf.setRoleId(role.getId()));
		roleFunctionDao.saveRoleFunctions(roleFunction);
	}

	@Override
	public void deleteRole(Long roleId) {
		roleDao.deleteRoleById(roleId);
	}

	@Override
	public List<Role> getRole(int page, int size) {
		List<Role> roles = roleDao.findRolesByPages(page, size);
		roles.forEach((role)->{
			List<RoleFunction> roleFunctions = roleFunctionDao.findRoleFunctionByRoleId(role.getId());
			StringBuilder functionIds = new StringBuilder();
			roleFunctions.forEach((rf)->{
				functionIds.append(rf.getFunctionId()).append(",");
			});
			
			if (functionIds.length()>1) {
				role.setFunctionIds(functionIds.deleteCharAt(functionIds.length()-1).toString());
			}
		});

		return roles;
	}

	@Override
	public Collection<User> getUsers(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> getUsers(Collection<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRole> getUserRoles(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRole> getUserRolesByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserRoles(Long userId, Long[] roleIds) {
		// TODO Auto-generated method stub
		
	}

}
