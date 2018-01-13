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
	
	//���ݵ�¼���û�����ѯ�Ƿ��и��û�
	@Override
	public List<User> selectUser(String username,String password) {
		String sql="select * from USER where USERNAME=? and PASSWORD=?";
//		User role = jdbcTemplate.queryForObject(sql, new Object[]{username,password}, User.class);
		/*//�����ݿ���û�з��ϵ����ݣ���resultSetΪ�գ�queryForObject�������׳��쳣org.springframework.dao.EmptyResultDataAccessException
		 * User user=(User) jdbcTemplate.queryForObject(sql, new Object[]{username,password},new RowMapper<Object>(){
		
			@Override
			public Object mapRow(ResultSet resultSet, int arg1) throws SQLException {
				if(resultSet==null){
					return null;
				}
				return mapRowHandler(resultSet);
			}
		});*/
		//������query�������õ�list����ʹ����ֻ��һ��User�����Ϊ�գ������ٽ����ж�
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

	//��ѯ���е��û�
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

	//����idɾ���û�
	@Override
	public void deleteById(Integer id) {
		String sql="delete from USER where ID=?";
		int i=jdbcTemplate.update(sql,id);
		System.out.println("ɾ���ɹ���Ӱ����"+i+"������");
	}

	//�����û���Ϣ
	@Override
	public void updateById(User user) {
		String sql="update USER set USERNAME=?,PASSWORD=? where ID=?";
		int i=jdbcTemplate.update(sql, user.getUsername(),user.getPassword(),user.getId());
		System.out.println("�޸ĳɹ���Ӱ����"+i+"������");
	}
	@Override
	public void updateById(Integer id, String username, String password) {
		String sql="update USER set USERNAME=?,PASSWORD=? where ID=?";
		int i=jdbcTemplate.update(sql, username,password,id);
		System.out.println("�޸ĳɹ���Ӱ����"+i+"������");
	};

	//�������û�
	@Override
	public void insert(User user) {
		String sql="insert into USER(USERNAME,PASSWORD) values(?,?)";
		int i=jdbcTemplate.update(sql, user.getUsername(),user.getPassword());
		System.out.println("�����ɹ���Ӱ����"+i+"������");
	}

}
