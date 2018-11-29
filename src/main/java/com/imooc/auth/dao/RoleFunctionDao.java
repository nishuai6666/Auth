package com.imooc.auth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.imooc.auth.common.BaseDao;
import com.imooc.auth.entity.RoleFunction;

public class RoleFunctionDao extends BaseDao {
	
	private class RoleFunctionMapper implements RowMapper<RoleFunction>{
		@Override
		public RoleFunction mapRow(ResultSet rs, int rowNum) throws SQLException {
			RoleFunction roleFunction = new RoleFunction();
			roleFunction.setId(rs.getLong("id"));
			roleFunction.setRoleId(rs.getLong("role_id"));
			roleFunction.setFunctionId(rs.getLong("function_id"));
			roleFunction.setStatus(rs.getInt("status"));
			return roleFunction;
		}
	}
	
	public RoleFunction findRoleFunctionById(Long id){
		String sql="select * from auth_role_function where id =?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RoleFunctionMapper());
	}
	
	public void saveRoleFunctions(Collection<RoleFunction> roleFunction){
		String sql="insert into auth_role_funtion(role_id,function_id, status) values (?,?,?)";
		List<Object[]>batchArgs = new ArrayList<>();
		roleFunction.forEach((rf)->{
			Object[] objs = new Object[3];
			objs[0]=rf.getRoleId();
			objs[1]=rf.getFunctionId();
			objs[2]=rf.getStatus();
			
			batchArgs.add(objs);
		});
		jdbcTemplate.batchUpdate(sql,batchArgs);
	}
	
	public List<RoleFunction> findRoleFunctionByRoleId(Long roleId){
		String sql="select * from auth_role_function where role_id =?";
		return jdbcTemplate.query(sql, new Object[]{roleId}, new RoleFunctionMapper());
	}
	
	public void deleteByRoleId(Long roleId){
		String sql="delete from auth_role_function where role_id= ?";
		jdbcTemplate.update(sql,roleId);
	}
	
	

}
