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
	
	//��֤�û���¼
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	//public String validate(User user,Model model){
	public String validate(String username,String password,Model model){
		User us = userService.selectUser(username,password);
		model.addAttribute("username", username);//Ϊ�˴���ǰ̨ҳ��
		model.addAttribute("password", password);
		if(us!=null){//���и��û�����ʾ��¼�ɹ�������ʾ�û����û���
			System.out.println(us.getUsername()+"-----"+us.getPassword());
			return "success";
		}else{//�������ڸ��û�������ʾ��¼ʧ�ܡ�����ʾ��������û����������Ա���
			System.out.println(username+"+++++"+password);
			return "failed";
		}
	}
	
	//��ǰ̨ҳ����ʾ�������û�
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String selectAll(Model model){
		List<User> userList=userService.selectAll();
		model.addAttribute("userList", userList);
		return "list";
	}
	//����idɾ���û�
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public String deleteById(@PathVariable("id")Integer id,Model model){
		userService.deleteById(id);
		List<User> list = userService.selectAll();
		model.addAttribute("userList", list);//ע��ת�����ض���urlʱ��б��/��û��б�ߵ�������б������Ŀ������б�����༶���µ�
		return "redirect:/list";//ע�⣺redirect:listֱָ������/list�ĸ�ҳ���еķ����������Ǿ�̬ҳ��
	}
	//����id�����û���Ϣǰ�Ȼ�����Ҫ�޸ĵ�����
	@RequestMapping(value="/updateShow/{id}",method=RequestMethod.PUT)
	public String updateShow(@PathVariable("id")Integer id,Model model){
//		model.addAttribute("id", id);
		User user = userService.selectById(id);
		model.addAttribute("user", user);
//		return "redirect:/update";		//http://localhost/sss/update
		return "/update";				///��ת��sss/WEB-INF/jsps/update.jspҳ��
//		return "update";				//http://localhost/sss/updateShow/update/10/q/q����ת��jspҳ�棬������RequestMappingҳ��
//		return "redirect:update";		//http://localhost/sss/updateShow/update
//		return "redirect:/updateJump";	//http://localhost/sss/updateJump��updateJumpΪRequestMapping��value��
	}
	/*//����id�����û���Ϣ
	@RequestMapping(value="/update/{username}/{password}",method=RequestMethod.PUT)
	public String updateById(user,Model model){
		userService.updateById(user);
		return "redirect:/list";
	}*/
	//����id�����û���Ϣ����ʵ��û���õ�url��ַ�е����ݡ�
	//����һ��
	@RequestMapping(value="/updateShow/update/{id}",method=RequestMethod.PUT)
//	public String updateById(Integer id,String username,String password){
	public String updateById(@PathVariable("id") Integer id,@RequestParam("username") String username,@RequestParam("password") String password){
		userService.updateById(id,username,password);
		return "redirect:/list";
	}
	/*//���������������ֵ���޸ĺ�spring�Զ���ǰ�˿ؼ���Ӧpojo�����ԣ�����õĶ���ֻ��Ҫ�ؼ���name��pojo����һ�¡�
	//	public String updateById(@RequestParam("id") Integer id,@RequestParam("username") String username,@RequestParam("password") String password){
	public String updateById(User user){
		userService.updateById(user);
		return "redirect:/list";
	}*/

	//�����½��û�
	@RequestMapping("/insertPro")
	public String insertJump(){
		return "insert";
	}
	//�½��û�
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(User user){
		userService.insert(user);
		return "redirect:/list";
	}
}
