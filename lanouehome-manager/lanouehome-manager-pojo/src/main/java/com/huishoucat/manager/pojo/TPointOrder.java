package com.huishoucat.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class TPointOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long pointOrderId;

	private Long userId;

	private Long itemId;

	private Long userAddressId;

	private String message;

	private Long courierNumber;

	private Integer state;

	private Long price;

	private Boolean isDeleted;

	private Date endTime;

	private Date createTime;

	private Date updateTime;

	public Long getPointOrderId() {
		return pointOrderId;
	}

	public void setPointOrderId(Long pointOrderId) {
		this.pointOrderId = pointOrderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message == null ? null : message.trim();
	}

	public Long getCourierNumber() {
		return courierNumber;
	}

	public void setCourierNumber(Long courierNumber) {
		this.courierNumber = courierNumber;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}