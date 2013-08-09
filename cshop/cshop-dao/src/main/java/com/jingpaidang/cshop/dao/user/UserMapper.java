/*
 * Copyright 2011-2012 the original author or authors.
 *
 *  http://www.jingpaidang.com
 */

package com.jingpaidang.cshop.dao.user;

import java.util.List;

import com.jingpaidang.cshop.domain.user.User;


/**
 * The Mapper of Example.
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 5/27/13
 */
public interface UserMapper {
	
	/**
	 * 保存注册的user
	 * @param user
	 */
	public void insert(User user) ;
	/**
	 * 按照id查找user
	 */
	public List<User> findById();
	
	public User findByUser(User user);

    public User findByEmail(String userEmail);

    public User findUserByAid(int aid);

    public User findUserByPid(String pid);
}
