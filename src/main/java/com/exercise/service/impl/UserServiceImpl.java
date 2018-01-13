package com.exercise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.dao.IUserDao;
import com.exercise.pojo.po.User;
import com.exercise.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	//根据登录的用户，查询是否有该用户
	@Override
	public User selectUser(String username,String password) {
		List<User> list = userDao.selectUser(username,password);
		User user=null;
		if(list.size()!=0){//若查询到的用户不为空，则从list中取出.若为空则直接返回null
			user=list.get(0);
		}
		return user;
	}
	//查询所有的用户
	@Override
	public List<User> selectAll() {
		List<User> list= userDao.selectAll();
		return list;
	}
	//根据id删除用户
	@Override
	public void deleteById(Integer id) {
		userDao.deleteById(id);
		
	}
	//根据id更新用户信息
	@Override
	public void updateById(User user) {
		userDao.updateById(user);
	}
	@Override
	public void updateById(Integer id,String username,String password) {
		userDao.updateById(id,username,password);
	}
	//插入新用户
	@Override
	public void insert(User user) {
		userDao.insert(user);
		
	}
	@Override
	public User selectById(Integer id) {
		List<User> list = userDao.selectUser(id);
		User user=null;
		if(list.size()!=0){//若查询到的用户不为空，则从list中取出.若为空则直接返回null
			user=list.get(0);
		}
		return user;
	}

}
