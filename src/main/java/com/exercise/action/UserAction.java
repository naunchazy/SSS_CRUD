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
	/**ע��ת�����ض���urlʱ����б������Ŀ������б�����༶���µġ�
	��������ڸ��౻������@RequestMappingʱ������û�б�����@RequestMappingʱ������б�߶���һ���ġ�*/
	/**����ע��redirectֻ���ڵ�ǰ����Ѱ��ƥ���url��������ȥѰ�Ҿ�̬ҳ��*/
	@RequestMapping("/returnLogin")
	public String returnLogin(){
//		return "login";				//1.
//		return "/login";			//2.->http://localhost/sss/returnLogin����Ϊ��ת�������Ե�ַû�иı䡿
		return "redirect:login";	//3.->http://localhost/sss/login����Ϊ�ض������Ե�ַ�ı䡣��
//		return "redirect:/login";	//4.->http://localhost/sss/login
//		return "redirect:/user/login";//5.
		/**�ڸ���δ������@RequestMapping�ǣ�1��2�ǵȼ۵ģ�3��4�ǵȼ۵�
		 * �ڸ��౻������@RequestMapping("/user")ʱ��3��5�ǵȼ۵�
		 * */
	}
	@RequestMapping("/login")
	public String index1(){
		return "login";
	}
	//��֤�û���¼
	//����һ��ͨ��ǰ�˿ؼ���name���ԣ��õ���Ӧ�ؼ���ֵ
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	public String validate(String username,String password,Model model){
		User us = userService.selectUser(username,password);
		model.addAttribute("username", username);//Ϊ�˴���ǰ̨ҳ��
		model.addAttribute("password", password);
		if(us!=null){//���и��û�����ʾ��¼�ɹ�������ʾ�û����û���
//			System.out.println(us.getUsername()+"-----"+us.getPassword());
			return "success";
		}else{//�������ڸ��û�������ʾ��¼ʧ�ܡ�����ʾ��������û����������Ա���
//			System.out.println(username+"+++++"+password);
			return "failed";
		}
	}
	/* ��������ͨ��po���װ��Ӧ�ؼ���ֵ��ע�⣺��ʹȱ��id����Ҳû�й�ϵ��
	 * public String validate(User user,Model model){
		User us = userService.selectUser(user.getUsername(),user.getPassword());
		model.addAttribute("username", user.getUsername());//Ϊ�˴���ǰ̨ҳ��
		model.addAttribute("password", user.getPassword());
		if(us!=null){
			return "success";
		}else{
			return "failed";
		}
	}*/
	
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
		model.addAttribute("userList", list);
		return "redirect:/list";//ע�⣺redirect:listֱָ������/list�ĸ�ҳ���еķ����������Ǿ�̬ҳ��
	}
	
	//�����û���Ϣǰ�Ȼ���ԭ���ݣ���user����������У�ǰ��ҳ���������ֵ��ʵ�ֻ��ԣ�����ת���޸�ҳ��
	@RequestMapping(value="/updateShow/{id}",method=RequestMethod.PUT)
	public String updateShow(@PathVariable("id")Integer id,Model model){
		User user = userService.selectById(id);
		model.addAttribute("user", user);
		return "/update";				
	}
	
	//����һ��
//	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	@RequestMapping(value="/updateShow/update/{id}",method=RequestMethod.PUT)
//	public String updateById(Integer id,String username,String password){
	public String updateById(@PathVariable("id") Integer id,@RequestParam("username") String username,@RequestParam("password") String password){
		userService.updateById(id,username,password);
		return "redirect:/list";
	}
	//����id�����û���Ϣ���÷�����δʹ��url��ַ�е����ݡ�������poʵ�����װǰ��ҳ��������û�����
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
