package com.jingpaidang.cshop.domain.user;

import java.util.Date;

/**
 * 平台授权账户
 *
 * @author jackchen
 */
public class Account {
    private Integer id;
    private String platformLoginId;
    private String platformLoginName;
    private String platformLoginPassword;
    private Integer platformFlag;
    private Integer jshopUserId;
    private String platformAccessToken;
    private String platformRefreshToken;
    private String accessTokenExpireTime;
    private String refreshTokenExpireTime;
    private Date createTime;
    private Date modifyTime;
    private String operator;
    private String platformShopName;

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatformLoginId() {
        return platformLoginId;
    }

    public void setPlatformLoginId(String platformLoginId) {
        this.platformLoginId = platformLoginId;
    }

    public String getPlatformLoginName() {
        return platformLoginName;
    }

    public void setPlatformLoginName(String platformLoginName) {
        this.platformLoginName = platformLoginName;
    }

    public Integer getPlatformFlag() {
        return platformFlag;
    }

    public void setPlatformFlag(Integer platformFlag) {
        this.platformFlag = platformFlag;
    }

    public Integer getJshopUserId() {
        return jshopUserId;
    }

    public void setJshopUserId(Integer jshopUserId) {
        this.jshopUserId = jshopUserId;
    }

    public String getPlatformAccessToken() {
        return platformAccessToken;
    }

    public void setPlatformAccessToken(String platformAccessToken) {
        this.platformAccessToken = platformAccessToken;
    }

    public String getPlatformRefreshToken() {
        return platformRefreshToken;
    }

    public void setPlatformRefreshToken(String platformRefreshToken) {
        this.platformRefreshToken = platformRefreshToken;
    }

    public String getAccessTokenExpireTime() {
        return accessTokenExpireTime;
    }

    public void setAccessTokenExpireTime(String accessTokenExpireTime) {
        this.accessTokenExpireTime = accessTokenExpireTime;
    }

    public String getRefreshTokenExpireTime() {
        return refreshTokenExpireTime;
    }

    public void setRefreshTokenExpireTime(String refreshTokenExpireTime) {
        this.refreshTokenExpireTime = refreshTokenExpireTime;
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

    public String getPlatformShopName() {
        return platformShopName;
    }

    public void setPlatformShopName(String platformShopName) {
        this.platformShopName = platformShopName;
    }

    public String getPlatformLoginPassword() {
        return platformLoginPassword;
    }

    public void setPlatformLoginPassword(String platformLoginPassword) {
        this.platformLoginPassword = platformLoginPassword;
    }
}

