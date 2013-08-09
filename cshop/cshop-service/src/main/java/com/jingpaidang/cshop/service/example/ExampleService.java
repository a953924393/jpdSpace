/*
 * Copyright 2011-2012 the original author or authors.
 *
 *  http://www.jingpaidang.com
 */

package com.jingpaidang.cshop.service.example;

import java.util.List;

import javax.annotation.Resource;

import com.jd.open.api.sdk.JdException;
import com.jingpaidang.cshop.common.plugins.Pagination;
import com.jingpaidang.cshop.rpc.jd.JdService;
import com.jingpaidang.cshop.rpc.jd.proxy.JdServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jingpaidang.cshop.dao.example.ExampleMapper;
import com.jingpaidang.cshop.domain.example.Example;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 5/28/13
 */
@Service("exampleService")
public class ExampleService {
    private static final Logger logger = LoggerFactory.getLogger(ExampleService.class);

    @Resource
    private ExampleMapper exampleMapper;

    @Resource
    private JdServiceProxy jdServiceProxy;

    /**
     * Query all examples.
     * @return List
     */
    public List<Example> getAllExamples(Pagination page) {
        logger.info("/==========================");
        logger.info("start get");
        return exampleMapper.findAllExamples();
    }

    public void testJdServiceProxy() throws JdException {
        JdService instance = jdServiceProxy.getInstance();
        instance.getCategorysOfUser("620081975f8c5c576529bb17f62ZZ3fa60210b66d32a9161694560449");
    }
    
}
