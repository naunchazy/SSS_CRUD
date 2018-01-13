package com.exercise.dao;

import java.util.List;

import com.exercise.pojo.po.User;

public interface IUserDao {

	//���ݵ�¼���û�����ѯ�Ƿ��и��û�
	List<User> selectUser(String username,String password);
	//��ѯ���е��û�
	List<User> selectAll();
	//����idɾ���û�
	void deleteById(Integer id);
	// ����id�����û���Ϣ
	void updateById(User user);
	void updateById(Integer id,String username,String password);
	//�������û�
	void insert(User user);
	List<User> selectUser(Integer id);
}
