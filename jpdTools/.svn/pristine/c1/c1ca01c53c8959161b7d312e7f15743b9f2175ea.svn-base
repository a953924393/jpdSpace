package com.jingpaidang.tool.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingpaidang.tool.service.user.UserService;
import com.jingpaidang.tool.user.User;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Resource(name ="userService")
	private UserService userService ;
	
	/**
	 * 
	 * @Title:toadd 
	 * @Description:TODO(跳转到添加) 
	 * @param:@return 
	 * @return:String 
	 * @throws 
	 * @Create: 2013-7-25 下午2:43:19
	 * @Author : Alex
	 */
	@RequestMapping("toadd")
	public String toadd(){
		return "/user/add" ;
	}
	/**
	 * 
	 * @Title:add 
	 * @Description:TODO(添加用户) 
	 * @param:@param model
	 * @param:@param user
	 * @param:@return 
	 * @return:String 
	 * @throws 
	 * @Create: 2013-7-25 下午2:25:13
	 * @Author : Alex
	 */
	@RequestMapping("add")
	public String add(ModelMap model,User user){
		userService.insert(user);
		return "redirect:/user/list" ;
	}
	/**
	 * 
	 * @Title:list 
	 * @Description:TODO(用户列表) 
	 * @param:@param model
	 * @param:@return 
	 * @return:String 
	 * @throws 
	 * @Create: 2013-7-25 下午2:25:34
	 * @Author : Alex
	 */
	@RequestMapping("list")
	public String list(ModelMap model){
		List<User> list = userService.getAllUser() ;
		model.addAttribute("list", list);
		return "/user/list" ;
	}
	/**
	 * 
	 * @Title:toupdate 
	 * @Description:TODO(跳转到修改页) 
	 * @param:@param model
	 * @param:@return 
	 * @return:String 
	 * @throws 
	 * @Create: 2013-7-25 下午2:51:38
	 * @Author : Alex
	 */
	@RequestMapping("toedit/{id}")
	public String toupdate(ModelMap model,@PathVariable Integer id){
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("id", id);
		return "/user/edit" ;
	}
	/**
	 * 
	 * @Title:update 
	 * @Description:TODO(修改用户) 
	 * @param:@param model
	 * @param:@param user
	 * @param:@param id
	 * @param:@return 
	 * @return:String 
	 * @throws 
	 * @Create: 2013-7-25 下午4:18:38
	 * @Author : Alex
	 */
	@RequestMapping("update/{id}")
	public String update(ModelMap model,User user,@PathVariable Integer id){
		userService.updateUser(user, id);
		return "redirect:/user/list";
	}
	/**
	 * 
	 * @Title:delete 
	 * @Description:TODO(删除用户) 
	 * @param:@param model
	 * @param:@param id
	 * @param:@return 
	 * @return:String 
	 * @throws 
	 * @Create: 2013-7-25 下午4:20:16
	 * @Author : Alex
	 */
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model,@PathVariable Integer id){
		userService.deleteUser(id);
		return "redirect:/user/list";
	}
}
