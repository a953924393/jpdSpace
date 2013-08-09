package com.jingpaidang.crm.domain.order;

import java.util.Date;

public class MemberOrder {
	/**
	 * 编号
	 */
	private Long id ;
	/**
	 * 商家编号
	 */
	private Long merchantNum ;
	/**
	 * 会员京东ID
	 */
	private String memberJdId ;
	/**
	 * 订单号
	 */
	private String orderId ;
	/**
	 * 收货人名称
	 */
	private String consigneeName ;
	/**
	 * 收货地址
	 */
	private String consigneeAddress ;
	/**
	 * 收货人手机号码
	 */
	private String consigneeTelephone ;
	/**
	 * 付款时间
	 */
	private Date paymentTime ;
	/**
	 * 订单总金额
	 */
	private String orderTotalPrice ;
	/**
	 * 信息创建时间
	 */
	private Date created ;
	/**
	 * 修改时间
	 */
	private Date modified ;
	/**
	 * 操作人
	 */
	private String operator ;
	/**
	 * 会员等级
	 */
	private String memberLevel ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMerchantNum() {
		return merchantNum;
	}
	public void setMerchantNum(Long merchantNum) {
		this.merchantNum = merchantNum;
	}
	public String getMemberJdId() {
		return memberJdId;
	}
	public void setMemberJdId(String memberJdId) {
		this.memberJdId = memberJdId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	public String getConsigneeTelephone() {
		return consigneeTelephone;
	}
	public void setConsigneeTelephone(String consigneeTelephone) {
		this.consigneeTelephone = consigneeTelephone;
	}
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(String orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	
}
