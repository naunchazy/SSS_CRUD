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
public class UserAction {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/")
	public String index(){
		return "login";
	}
	
	//验证用户登录
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	//public String validate(User user,Model model){
	public String validate(String username,String password,Model model){
		User us = userService.selectUser(username,password);
		model.addAttribute("username", username);//为了传到前台页面
		model.addAttribute("password", password);
		if(us!=null){//若有该用户则显示登录成功，并显示用户的用户名
			System.out.println(us.getUsername()+"-----"+us.getPassword());
			return "success";
		}else{//若不存在该用户，则显示登录失败。并显示刚填入的用户名和密码以便检查
			System.out.println(username+"+++++"+password);
			return "failed";
		}
	}
	
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
		model.addAttribute("userList", list);//注意转发和重定向url时有斜线/和没有斜线的区别，有斜线是项目级别，无斜线是类级别下的
		return "redirect:/list";//注意：redirect:list指直接跳到/list的该页面中的方法，而不是静态页面
	}
	//根据id更新用户信息前先回显需要修改的数据
	@RequestMapping(value="/updateShow/{id}",method=RequestMethod.PUT)
	public String updateShow(@PathVariable("id")Integer id,Model model){
//		model.addAttribute("id", id);
		User user = userService.selectById(id);
		model.addAttribute("user", user);
//		return "redirect:/update";		//http://localhost/sss/update
		return "/update";				///跳转到sss/WEB-INF/jsps/update.jsp页面
//		return "update";				//http://localhost/sss/updateShow/update/10/q/q。跳转到jsp页面，而不是RequestMapping页面
//		return "redirect:update";		//http://localhost/sss/updateShow/update
//		return "redirect:/updateJump";	//http://localhost/sss/updateJump。updateJump为RequestMapping的value。
	}
	/*//根据id更新用户信息
	@RequestMapping(value="/update/{username}/{password}",method=RequestMethod.PUT)
	public String updateById(user,Model model){
		userService.updateById(user);
		return "redirect:/list";
	}*/
	//根据id更新用户信息。其实并没有用到url地址中的数据。
	//方法一：
	@RequestMapping(value="/updateShow/update/{id}",method=RequestMethod.PUT)
//	public String updateById(Integer id,String username,String password){
	public String updateById(@PathVariable("id") Integer id,@RequestParam("username") String username,@RequestParam("password") String password){
		userService.updateById(id,username,password);
		return "redirect:/list";
	}
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
