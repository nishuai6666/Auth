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
	
	/**
	 * 保存角色信息,同时保存角色对应的功能
	 * @param role 角色
	 * @param roleFunction 角色对应的功能（即角色功能的关联关系）
	 */
	@Override
	public void addRole(Role role, Collection<RoleFunction> roleFunction) {
		roleDao.saveRole(role);
		roleFunction.forEach((rf) -> rf.setRoleId(role.getId()));
		roleFunctionDao.saveRoleFunctions(roleFunction);
	}
	
	/**
	 * 编辑角色信息,同时保存角色对应的功能
	 * @param role 角色
	 * @param roleFunction 角色对应的功能（即角色功能的关联关系）
	 */
	@Override
	public void eidtRole(Role role, Collection<RoleFunction> roleFunction) {
		roleDao.updateRole(role);
		roleFunctionDao.deleteByRoleId(role.getId());
		roleFunction.forEach((rf) ->rf.setRoleId(role.getId()));
		roleFunctionDao.saveRoleFunctions(roleFunction);
	}
	
	/**
	 * 删除角色信息
	 * @param roleId
	 */
	@Override
	public void deleteRole(Long roleId) {
		roleDao.deleteRoleById(roleId);
		roleFunctionDao.deleteByRoleId(roleId);
	}
	
	/**
	 * 分页查询角色信息
	 * @param page 当前页码
	 * @param size 每页信息条数
	 * @return 角色集合
	 */
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
	
	/**
	 * 根据ID集合查询角色信息
	 * @param ids 角色ID集合
	 * @return 角色集合
	 */
	@Override
	public List<Role> getUsers(Collection<Long> ids) {
		return roleDao.findRoleByIds(ids);
	}
	
	/**
	 * 根据用户ID查询角色功能对应关系
	 * @param roleId 角色ID
	 * @return 角色功能对应关系
	 */
	@Override
	public List<RoleFunction> getRoleFunction(Long roleId) {
		return roleFunctionDao.findRoleFunctionByRoleId(roleId);
	}

}
