package com.jingpaidang.tool.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingpaidang.tool.dao.user.UserMapper;
import com.jingpaidang.tool.service.user.UserService;
import com.jingpaidang.tool.user.User;
/**
 * 
 * @ClassName:	UserServiceImpl 
 * @Description:TODO(用户业务实现类) 
 * @author:	Alex
 * @date:	2013-7-25 下午2:21:38 
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	public void insert(User user) {
		userMapper.insert(user);
	}

	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}

	public void updateUser(User user, Integer id) {
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("user", user) ;
		paras.put("userId", id);
		userMapper.updateUser(paras);
	}

	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	public void deleteUser(Integer id) {
		userMapper.deleteUser(id);
	}
	
}
