/*
 * Copyright 2011-2012 the original author or authors.
 *
 *  http://www.jingpaidang.com
 */

package com.jingpaidang.cshop.domain.category;

import java.io.Serializable;
import java.util.Date;

/**
 * 类目属性值实体
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/25/13
 */
public class CategoryPropertyValue implements Serializable {
    private long id;
    private long propertyId;
    private long propertyValueId;
    private String propertyValue;
    private Date createTime;
    private Date modifyTime;
    private String operator;

    public CategoryPropertyValue() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public long getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(long propertyValueId) {
        this.propertyValueId = propertyValueId;
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
