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
	
	//���ݵ�¼���û�����ѯ�Ƿ��и��û�
	@Override
	public User selectUser(String username,String password) {
		List<User> list = userDao.selectUser(username,password);
		User user=null;
		if(list.size()!=0){//����ѯ�����û���Ϊ�գ����list��ȡ��.��Ϊ����ֱ�ӷ���null
			user=list.get(0);
		}
		return user;
	}
	//��ѯ���е��û�
	@Override
	public List<User> selectAll() {
		List<User> list= userDao.selectAll();
		return list;
	}
	//����idɾ���û�
	@Override
	public void deleteById(Integer id) {
		userDao.deleteById(id);
		
	}
	//����id�����û���Ϣ
	@Override
	public void updateById(User user) {
		userDao.updateById(user);
	}
	@Override
	public void updateById(Integer id,String username,String password) {
		userDao.updateById(id,username,password);
	}
	//�������û�
	@Override
	public void insert(User user) {
		userDao.insert(user);
		
	}
	@Override
	public User selectById(Integer id) {
		List<User> list = userDao.selectUser(id);
		User user=null;
		if(list.size()!=0){//����ѯ�����û���Ϊ�գ����list��ȡ��.��Ϊ����ֱ�ӷ���null
			user=list.get(0);
		}
		return user;
	}

}
