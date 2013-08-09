package com.jingpaidang.cshop.domain.category;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品类目实体类
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/19/13
 */
public class ItemCategory implements Serializable {
    private int id;
    private long categoryId;
    private String categoryName;
    private long parentId;
    private int level;

    private boolean isParent;
    private int platformFlag;
    private Date createTime;
    private Date modifyTime;
    private String operator;

    public ItemCategory() {
    }

    public ItemCategory(long categoryId, int platformFlag) {
        this.categoryId = categoryId;
        this.platformFlag = platformFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
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

    public int getPlatformFlag() {
        return platformFlag;
    }

    public void setPlatformFlag(int platformFlag) {
        this.platformFlag = platformFlag;
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
