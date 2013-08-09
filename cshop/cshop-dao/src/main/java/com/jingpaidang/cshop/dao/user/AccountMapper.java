/*
 * Copyright 2011-2012 the original author or authors.
 *
 *  http://www.jingpaidang.com
 */

package com.jingpaidang.cshop.dao.user;

import java.util.List;

import com.jingpaidang.cshop.domain.user.Account;
import com.jingpaidang.cshop.domain.user.User;


/**
 * The Mapper of Account.
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 5/27/13
 */
public interface AccountMapper {

    /**
     * 保存平台授权信息
     *
     * @param account
     */
    public void insert(Account account);

    /**
     *
     * @param account
     */
    public void update(Account account );


    public Account findByPlatformLoginId(String platformLoginId);


    /**
     * 根据主键查询账户
     *
     * @param id
     * @return
     */
    public Account findAccountById(int id);

    /**
     * 按照refreshToken 得到平台授权按信息
     */
    public Account findByRefreshToken(String refreshToken);

    public Account findByAccessToken(String accessToken);

    /**
     * 根据用户id查询得到所有
     *
     * @param uid
     * @return
     */
    public List<Account> findByUserId(Integer uid);

    public List<Account> findAll();

    public User findUserByAid(Integer aid);
}
