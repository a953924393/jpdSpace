/*
 * Copyright 2011-2012 the original author or authors.
 *
 *  http://www.jingpaidang.com
 */

package com.jingpaidang.cshop.domain.convert;

import java.io.Serializable;
import java.util.Date;

/**
 * 转换规则详情实体
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/25/13
 */
public class ConvertRuleDetail implements Serializable {
    private long id;
    private long ruleId;
    private long targetPropertyId;
    private long targetPropertyValueId;
    private long srcPropertyId;
    private long srcPropertyValueId;
    private Date createTime;
    private Date modifyTime;
    private String operator;

    public ConvertRuleDetail() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRuleId() {
        return ruleId;
    }

    public void setRuleId(long ruleId) {
        this.ruleId = ruleId;
    }

    public long getTargetPropertyId() {
        return targetPropertyId;
    }

    public void setTargetPropertyId(long targetPropertyId) {
        this.targetPropertyId = targetPropertyId;
    }

    public long getSrcPropertyId() {
        return srcPropertyId;
    }

    public void setSrcPropertyId(long srcPropertyId) {
        this.srcPropertyId = srcPropertyId;
    }

    public long getTargetPropertyValueId() {
        return targetPropertyValueId;
    }

    public void setTargetPropertyValueId(long targetPropertyValueId) {
        this.targetPropertyValueId = targetPropertyValueId;
    }

    public long getSrcPropertyValueId() {
        return srcPropertyValueId;
    }

    public void setSrcPropertyValueId(long srcPropertyValueId) {
        this.srcPropertyValueId = srcPropertyValueId;
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
