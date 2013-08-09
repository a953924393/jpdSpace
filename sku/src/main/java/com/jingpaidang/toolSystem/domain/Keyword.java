package com.jingpaidang.toolSystem.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 7/16/13
 * Time: 6:45 PM
 */
public class Keyword {
    private int id;

    private String keyName;

    private Date createTime;

    private Date updateTime;

    private int number;

    private String status;


    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}