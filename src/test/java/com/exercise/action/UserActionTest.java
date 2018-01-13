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
//		if(us!=null){//若有该用户则显示登录成功
//			System.out.println(us.getUsername()+"-----"+us.getPassword());
//		}else{//若不存在该用户，则显示登录失败。并显示填入的用户名和密码以便检查
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
