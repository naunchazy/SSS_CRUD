package com.exercise.dao;

import java.util.List;

import com.exercise.pojo.po.User;

public interface IUserDao {

	//根据登录的用户，查询是否有该用户
	List<User> selectUser(String username,String password);
	//查询所有的用户
	List<User> selectAll();
	//根据id删除用户
	void deleteById(Integer id);
	// 根据id更新用户信息
	void updateById(User user);
	void updateById(Integer id,String username,String password);
	//插入新用户
	void insert(User user);
	List<User> selectUser(Integer id);
}
