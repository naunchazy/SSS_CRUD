package com.exercise.action;

import java.util.List;

import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking.UpdateIndexCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exercise.pojo.po.User;
import com.exercise.service.IUserService;
import com.sun.javafx.collections.MappingChange.Map;

@Controller
//@RequestMapping("/user")
public class UserAction {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/")
	public String index(){
		return "login";
	}
	/**注意转发和重定向url时，有斜线是项目级别，无斜线是类级别下的。
	【是相对于该类被定义了@RequestMapping时，若类没有被定义@RequestMapping时，有无斜线都是一样的】*/
	/**还需注意redirect只会在当前类中寻找匹配的url，而不会去寻找静态页面*/
	@RequestMapping("/returnLogin")
	public String returnLogin(){
//		return "login";				//1.
//		return "/login";			//2.->http://localhost/sss/returnLogin【因为是转发，所以地址没有改变】
		return "redirect:login";	//3.->http://localhost/sss/login【因为重定向，所以地址改变。】
//		return "redirect:/login";	//4.->http://localhost/sss/login
//		return "redirect:/user/login";//5.
		/**在该类未被设置@RequestMapping是，1、2是等价的，3、4是等价的
		 * 在该类被设置了@RequestMapping("/user")时，3、5是等价的
		 * */
	}
	@RequestMapping("/login")
	public String index1(){
		return "login";
	}
	//验证用户登录
	//方法一：通过前端控件的name属性，得到对应控件的值
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	public String validate(String username,String password,Model model){
		User us = userService.selectUser(username,password);
		model.addAttribute("username", username);//为了传到前台页面
		model.addAttribute("password", password);
		if(us!=null){//若有该用户则显示登录成功，并显示用户的用户名
//			System.out.println(us.getUsername()+"-----"+us.getPassword());
			return "success";
		}else{//若不存在该用户，则显示登录失败。并显示刚填入的用户名和密码以便检查
//			System.out.println(username+"+++++"+password);
			return "failed";
		}
	}
	/* 方法二：通过po类封装对应控件的值，注意：即使缺少id属性也没有关系。
	 * public String validate(User user,Model model){
		User us = userService.selectUser(user.getUsername(),user.getPassword());
		model.addAttribute("username", user.getUsername());//为了传到前台页面
		model.addAttribute("password", user.getPassword());
		if(us!=null){
			return "success";
		}else{
			return "failed";
		}
	}*/
	
	//在前台页面显示出所有用户
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String selectAll(Model model){
		List<User> userList=userService.selectAll();
		model.addAttribute("userList", userList);
		return "list";
	}
	//根据id删除用户
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public String deleteById(@PathVariable("id")Integer id,Model model){
		userService.deleteById(id);
		List<User> list = userService.selectAll();
		model.addAttribute("userList", list);
		return "redirect:/list";//注意：redirect:list指直接跳到/list的该页面中的方法，而不是静态页面
	}
	
	//更新用户信息前先回显原数据，将user对象放入域中，前端页面就能渠道值，实现回显，再跳转到修改页面
	@RequestMapping(value="/updateShow/{id}",method=RequestMethod.PUT)
	public String updateShow(@PathVariable("id")Integer id,Model model){
		User user = userService.selectById(id);
		model.addAttribute("user", user);
		return "/update";				
	}
	
	//方法一：
//	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	@RequestMapping(value="/updateShow/update/{id}",method=RequestMethod.PUT)
//	public String updateById(Integer id,String username,String password){
	public String updateById(@PathVariable("id") Integer id,@RequestParam("username") String username,@RequestParam("password") String password){
		userService.updateById(id,username,password);
		return "redirect:/list";
	}
	//根据id更新用户信息。该方法中未使用url地址中的数据。而是用po实体类封装前端页面填入的用户数据
	/*//方法二：【传入的值是修改后被spring自动将前端控件对应pojo类属性，打包好的对象。只需要控件的name与pojo属性一致】
	//	public String updateById(@RequestParam("id") Integer id,@RequestParam("username") String username,@RequestParam("password") String password){
	public String updateById(User user){
		userService.updateById(user);
		return "redirect:/list";
	}*/

	//进入新建用户
	@RequestMapping("/insertPro")
	public String insertJump(){
		return "insert";
	}
	//新建用户
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(User user){
		userService.insert(user);
		return "redirect:/list";
	}
}
