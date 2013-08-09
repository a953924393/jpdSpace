/*
 * Copyright 2011-2012 the original author or authors.
 *
 *  http://www.jingpaidang.com
 */

package com.jingpaidang.cshop.domain.ware;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品实体类
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/17/13
 */
public class WareItem implements Serializable{
    private Long id;
    private Long itemId; //商品id
    private String itemName; //商品名称
    private String categoryName; //网店类目
    private Boolean moved; //是否搬家
    private Long storage; //库存
    private String img; //主图
    private String url; //商品url
    private String price ; //商品售价
    /**
     * 网店类目ID
     */
    private Long categoryId;
    /**
     * 京商店店铺ID
     */
    private long jshopAccountId;
    /**
     * 京商店用户ID
     */
    private long jshopUserId ;
    /**
     * 商品描述
     */
    private String description  ;
    /**
     * 进货价
     */
    private String costPrice ;
    /**
     * 市场价格
     */
    private String marketPrice ;
    /**
     * 创建时间
     */
    private Date created ;
    /**
     * 修改时间
     */
    private Date modified ;
    /**
     * 商品对外ID
     */
    private String outerId ;
    /**
     * 商品状态 	onsale在售 instock库中
     */
    private String status ;
    
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public WareItem() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean isMoved() {
        return moved;
    }

    public String getMoved() {
        return moved.toString();
    }

    public void setMoved(Boolean moved) {
        this.moved = moved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getStorage() {
        return storage;
    }

    public void setStorage(Long storage) {
        this.storage = storage;
    }

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public long getJshopAccountId() {
		return jshopAccountId;
	}

	public void setJshopAccountId(long jshopAccountId) {
		this.jshopAccountId = jshopAccountId;
	}

	public long getJshopUserId() {
		return jshopUserId;
	}

	public void setJshopUserId(long jshopUserId) {
		this.jshopUserId = jshopUserId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
    
}
