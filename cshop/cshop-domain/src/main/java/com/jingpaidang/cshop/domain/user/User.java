package com.jingpaidang.cshop.domain.user;

import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String jshopUserName;
    private String jshopUserPassword;
    private String userCompanyName;
    private String userCompanyAddress;
    private String userTelephone;
    private String userEmail;
    private String userQq;
    private Date createTime;
    private Date modifyTime;
    private String operator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJshopUserName() {
        return jshopUserName;
    }

    public void setJshopUserName(String jshopUserName) {
        this.jshopUserName = jshopUserName;
    }

    public String getJshopUserPassword() {
        return jshopUserPassword;
    }

    public void setJshopUserPassword(String jshopUserPassword) {
        this.jshopUserPassword = jshopUserPassword;
    }

    public String getUserCompanyName() {
        return userCompanyName;
    }

    public void setUserCompanyName(String userCompanyName) {
        this.userCompanyName = userCompanyName;
    }

    public String getUserCompanyAddress() {
        return userCompanyAddress;
    }

    public void setUserCompanyAddress(String userCompanyAddress) {
        this.userCompanyAddress = userCompanyAddress;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
