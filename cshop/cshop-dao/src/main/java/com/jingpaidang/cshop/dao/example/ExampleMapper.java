/*
 * Copyright 2011-2012 the original author or authors.
 *
 *  http://www.jingpaidang.com
 */

package com.jingpaidang.cshop.dao.example;

import com.jingpaidang.cshop.domain.example.Example;

import java.util.List;

/**
 * The Mapper of Example.
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 5/27/13
 */
public interface ExampleMapper {
    /**
     * find all examples.
     * @return
     */
    public List<Example> findAllExamples();
}
