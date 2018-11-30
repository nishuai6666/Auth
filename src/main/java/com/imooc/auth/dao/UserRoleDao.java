package com.imooc.auth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.imooc.auth.common.BaseDao;
import com.imooc.auth.entity.UserRole;

public class UserRoleDao extends BaseDao {
	
	private class UserRoleMapper implements RowMapper<UserRole>{
		@Override
		public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserRole userRole = new UserRole();
			userRole.setId(rs.getLong("id"));
			userRole.setRoleId(rs.getLong("role_id"));
			userRole.setUserId(rs.getLong("user_id"));
			return userRole;
		}
	}
	
	public List<UserRole> findUserRoles(int page,int size){
		String sql="select * from auth_user_role order by user_id limit?,?";
		return jdbcTemplate.query(sql, new Object[]{(page-1)*size,size},new UserRoleMapper());
	}

	/**
     * 根据userId查询用户角色
     * @param userId
     * @return
     */
    public List<UserRole> findUserRoleByUserId(Long userId){
        String sql="select * from auth_user_role where user_id =?";
        try {
            return jdbcTemplate.query(sql,new Object[]{userId},new UserRoleMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public void saveUserRoles(Collection<UserRole> userRole){
		String sql="insert into auth_user_role(user_id,role_id) values (?,?)";
		List<Object[]>batchArgs = new ArrayList<>();
		userRole.forEach((uf)->{
			Object[] objs = new Object[2];
			objs[0]=uf.getUserId();
			objs[1]=uf.getRoleId();
			
			batchArgs.add(objs);
		});
		jdbcTemplate.batchUpdate(sql,batchArgs);
	}
	
	public void deleteByUserId(Long userId){
		String sql="delete from auth_user_role where user_id= ?";
		jdbcTemplate.update(sql,userId);
	}
	
	public UserRole findUserRoleById(Long id){
		String sql="select * from auth_user_role where id =?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRoleMapper());
	}
}
