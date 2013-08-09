package com.jingpaidang.sku.domain.example;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 8/6/13
 * Time: 12:58 PM
 */
public class Example {
    private int id;
    private Date createTime;
    private boolean married;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
