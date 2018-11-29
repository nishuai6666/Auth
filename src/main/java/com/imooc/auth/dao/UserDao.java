package com.imooc.auth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jdbc.core.RowMapper;

import com.imooc.auth.common.BaseDao;
import com.imooc.auth.entity.User;

public class UserDao extends BaseDao {
	
	private class UserMapper implements RowMapper<User>{
		@Override
		public User mapRow(ResultSet rs, int index) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setName(rs.getString("name"));
			user.setPwd(rs.getString("pwd"));
			return user;
		}
		
	}
	
	
	/**
	 * 根据用户名,密码查询用户，用于登录
	 * @param name 用户名
	 * @param pwd 密码
	 * @return 查询到的唯一用户实体
	 */
	public User findUser(String name, String pwd){
		String sql="select * from auth_user where name=?and pwd=?";
		try{
			return jdbcTemplate.queryForObject(sql,new Object[]{name,pwd}, new UserMapper());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void save(User user){
		String sql="insert into auth_user(id, name, pwd) values (?,?,?)";
		jdbcTemplate.update(sql, user.getId(), user.getName(), user.getPwd());
	}
	
	public void deleteById(Long id){
		String sql="delete from auth_user where id= ?";
		jdbcTemplate.update(sql,id);
	}
	
	public void update(User user){
		String sql="update auth_user set name = ?,pwd = ? where id= ?";
		jdbcTemplate.update(sql, user.getName(), user.getPwd(),user.getId());
	}
	
	public User findById(Long id){
		String sql="select * from auth_user where id =?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
	}
	
	public Collection<User> findByIds(Collection<Long> ids){
		StringBuilder sql= new StringBuilder( "select * from auth_user where id in(");
		Object[] args = new Object[ids.size()];
		//处理多线程并发是出现的变量
		AtomicInteger index = new AtomicInteger(0);
		ids.forEach((id)->{
			sql.append(id).append("?,");
			args[index.getAndIncrement()] = id; 
			});
		sql.deleteCharAt(sql.length()-2);
		sql.append(")");
	 	return jdbcTemplate.query(sql.toString(), ids.toArray(new Object[0]), new UserMapper());
	}
	
	
	
	
}
