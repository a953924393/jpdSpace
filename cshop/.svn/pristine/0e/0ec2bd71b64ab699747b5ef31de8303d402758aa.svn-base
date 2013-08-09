/*
 * Copyright 2011-2012 the original author or authors.
 *
 *  http://www.jingpaidang.com
 */

package com.jingpaidang.cshop.domain.convert;


import com.jingpaidang.cshop.common.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 平台转换规则实体
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/25/13
 */
public class PlatformConvertRule implements Serializable {
    private long id;
    private String ruleEncoding;
    private String ruleName;
    private long srcAccountId;
    private long srcCategoryId;
    private long targetAccountId;
    private long targetCategoryId;
    private long jshopUserId;
    private Date createTime;
    private Date modifyTime;
    private String operator;

    /**
     * 用于页面展示相应的名称
     */
    private String srcShopName;
    private String targetShopName;
    private String srcCategoryName;
    private String targetCategoryName;

    public PlatformConvertRule() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRuleEncoding() {
        return ruleEncoding;
    }

    public void setRuleEncoding(String ruleEncoding) {
        this.ruleEncoding = ruleEncoding;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public long getSrcAccountId() {
        return srcAccountId;
    }

    public void setSrcAccountId(long srcAccountId) {
        this.srcAccountId = srcAccountId;
    }

    public long getSrcCategoryId() {
        return srcCategoryId;
    }

    public void setSrcCategoryId(long srcCategoryId) {
        this.srcCategoryId = srcCategoryId;
    }

    public long getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public long getTargetCategoryId() {
        return targetCategoryId;
    }

    public void setTargetCategoryId(long targetCategoryId) {
        this.targetCategoryId = targetCategoryId;
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

    public long getJshopUserId() {
        return jshopUserId;
    }

    public void setJshopUserId(long jshopUserId) {
        this.jshopUserId = jshopUserId;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCreateTimeDesc() {
        return this.createTime != null ? DateUtil.date2str(this.createTime) : "";
    }

    public String getSrcShopName() {
        return srcShopName;
    }

    public void setSrcShopName(String srcShopName) {
        this.srcShopName = srcShopName;
    }

    public String getTargetShopName() {
        return targetShopName;
    }

    public void setTargetShopName(String targetShopName) {
        this.targetShopName = targetShopName;
    }

    public String getSrcCategoryName() {
        return srcCategoryName;
    }

    public void setSrcCategoryName(String srcCategoryName) {
        this.srcCategoryName = srcCategoryName;
    }

    public String getTargetCategoryName() {
        return targetCategoryName;
    }

    public void setTargetCategoryName(String targetCategoryName) {
        this.targetCategoryName = targetCategoryName;
    }
}
