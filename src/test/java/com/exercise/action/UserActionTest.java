//package com.exercise.action;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.exercise.pojo.po.User;
//import com.exercise.service.IUserService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:springmvc-servlet.xml")
//public class UserActionTest {
//
//	@Autowired
//	private IUserService userService;
//	
//	@Test
//	public void validate(){
//		String username="tom";
//		String password="123";
//		User us = userService.selectUser(username,password);
//		if(us!=null){//���и��û�����ʾ��¼�ɹ�
//			System.out.println(us.getUsername()+"-----"+us.getPassword());
//		}else{//�������ڸ��û�������ʾ��¼ʧ�ܡ�����ʾ������û����������Ա���
//			System.out.println(us.getUsername()+"+++++"+us.getPassword());
//		}
//		
//	}
//	
//	@Test
//	public void selectAllTest(){
//		List<User> list= userService.selectAll();
//		System.out.println(list.size());
//	}
//	
//	
//}
