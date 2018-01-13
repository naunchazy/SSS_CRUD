package com.exercise.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.exercise.dao.IUserDao;
import com.exercise.pojo.po.User;

@Repository
/*public class UserDaoImpl extends JdbcDaoSupport implements IUserDao{
 	@Autowired
 	private DataSource dataSource
 	getTemplate()
 	*/
public class UserDaoImpl implements IUserDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//根据登录的用户，查询是否有该用户
	@Override
	public List<User> selectUser(String username,String password) {
		String sql="select * from USER where USERNAME=? and PASSWORD=?";
//		User role = jdbcTemplate.queryForObject(sql, new Object[]{username,password}, User.class);
		/*//当数据库中没有符合的数据，即resultSet为空，queryForObject方法会抛出异常org.springframework.dao.EmptyResultDataAccessException
		 * User user=(User) jdbcTemplate.queryForObject(sql, new Object[]{username,password},new RowMapper<Object>(){
		
			@Override
			public Object mapRow(ResultSet resultSet, int arg1) throws SQLException {
				if(resultSet==null){
					return null;
				}
				return mapRowHandler(resultSet);
			}
		});*/
		//所以用query方法，得到list。即使其中只有一个User对象或为空，后续再进行判断
		List<User> list=jdbcTemplate.query(sql, new Object[]{username,password}, new RowMapper<User>(){
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return  mapRowHandler(rs);
			}
			
		});
		return list;
//		return role;
	}

	private User mapRowHandler(ResultSet resultSet) throws  SQLException{
        User user = new User();
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setId(resultSet.getInt("id"));
        return  user;
	}
	
	@Override
	public List<User> selectUser(Integer id) {
		String sql="select * from USER where ID=?";
		List<User> list=jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<User>(){
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return  mapRowHandler(rs);
			}
			
		});
		return list;
	}

	//查询所有的用户
	@Override
	public List<User> selectAll() {
		String sql="select * from USER";
		List<User> list=jdbcTemplate.query(sql,new RowMapper<User>(){
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return  mapRowHandler(rs);
			}
			
		});
		
		return list;
	}

	//根据id删除用户
	@Override
	public void deleteById(Integer id) {
		String sql="delete from USER where ID=?";
		int i=jdbcTemplate.update(sql,id);
		System.out.println("删除成功！影响了"+i+"行数据");
	}

	//更新用户信息
	@Override
	public void updateById(User user) {
		String sql="update USER set USERNAME=?,PASSWORD=? where ID=?";
		int i=jdbcTemplate.update(sql, user.getUsername(),user.getPassword(),user.getId());
		System.out.println("修改成功！影响了"+i+"行数据");
	}
	@Override
	public void updateById(Integer id, String username, String password) {
		String sql="update USER set USERNAME=?,PASSWORD=? where ID=?";
		int i=jdbcTemplate.update(sql, username,password,id);
		System.out.println("修改成功！影响了"+i+"行数据");
	};

	//插入新用户
	@Override
	public void insert(User user) {
		String sql="insert into USER(USERNAME,PASSWORD) values(?,?)";
		int i=jdbcTemplate.update(sql, user.getUsername(),user.getPassword());
		System.out.println("新增成功！影响了"+i+"行数据");
	}

}
