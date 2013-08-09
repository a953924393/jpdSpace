/*
 * Copyright 2011-2012 the original author or authors.
 *
 *  http://www.jingpaidang.com
 */

package com.jingpaidang.cshop.domain.category;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 类目属性实体
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/25/13
 */
public class CategoryProperty implements Serializable {
    private long id;
    private long propertyId;
    private String propertyName;
    private long categoryId;
    private int platformFlag;
    private boolean isNeedConvert;
    private String isRequired;
    private Date createTime;
    private Date modifyTime;
    private String operator;

    //属性值集
    List<CategoryPropertyValue> categoryPropertyValues = null;

    public CategoryProperty() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public int getPlatformFlag() {
        return platformFlag;
    }

    public void setPlatformFlag(int platformFlag) {
        this.platformFlag = platformFlag;
    }

    public boolean isNeedConvert() {
        return isNeedConvert;
    }

    public void setNeedConvert(boolean needConvert) {
        isNeedConvert = needConvert;
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

    public List<CategoryPropertyValue> getCategoryPropertyValues() {
        return categoryPropertyValues;
    }

    public void setCategoryPropertyValues(List<CategoryPropertyValue> categoryPropertyValues) {
        this.categoryPropertyValues = categoryPropertyValues;
    }

    public String getRequired() {
        return isRequired;
    }

    public void setRequired(String required) {
        isRequired = required;
    }

    public int getIsNeedConvertDesc() {
        return this.isNeedConvert ? 1 : 0;
    }
}
