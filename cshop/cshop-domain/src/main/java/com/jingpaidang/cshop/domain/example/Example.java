/*
 * Copyright 2011-2012 the original author or authors.
 *
 *  http://www.jingpaidang.com
 */

package com.jingpaidang.cshop.domain.example;

/**
 * Example entity for test.
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 5/27/13
 */
public class Example {
    private int id;
    private String name;
    private String desc;

    /**
     * Constructors.
     */
    public Example() {
    }
    public Example(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
