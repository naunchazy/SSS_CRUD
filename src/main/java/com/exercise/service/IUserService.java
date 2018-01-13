package com.exercise.service;

import java.util.List;

import com.exercise.pojo.po.User;

public interface IUserService {

	// ���ݵ�¼���û�����ѯ�Ƿ��и��û�
	User selectUser(String username, String password);
	// ��ѯ���е��û�
	List<User> selectAll();
	// ����idɾ���û�
	void deleteById(Integer id);
	// ����id�����û���Ϣ
	void updateById(User user);
	void updateById(Integer id,String username,String password);
	// �������û�
	void insert(User user);
	User selectById(Integer id);
}
