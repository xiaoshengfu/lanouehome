package com.huishoucat.manager.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TWasteOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	private TRecycler recycler;

	private TUserAddress userAddress;

	private List<TWasteOrderItem> wasteOrderItems;

	private Long wasteOrderId;

	private Long userId;

	private Long recyclerId;

	private Date reserveTime;

	private Long userAddressId;

	private String message;

	private Long estimatePrice;

	private Long realPrice;

	private Long estimatePoint;

	private Long realPoint;

	private Integer state;

	private Date confirmTime;

	private Date payTime;

	private Date endTime;

	private Date invalidTime;

	private String invalidReason;

	private Boolean isDeleted;

	private Date createTime;

	private Date updateTime;

	public TRecycler getRecycler() {
		return recycler;
	}

	public void setRecycler(TRecycler recycler) {
		this.recycler = recycler;
	}

	public TUserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(TUserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public List<TWasteOrderItem> getWasteOrderItems() {
		return wasteOrderItems;
	}

	public void setWasteOrderItems(List<TWasteOrderItem> wasteOrderItems) {
		this.wasteOrderItems = wasteOrderItems;
	}

	public Long getWasteOrderId() {
		return wasteOrderId;
	}

	public void setWasteOrderId(Long wasteOrderId) {
		this.wasteOrderId = wasteOrderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRecyclerId() {
		return recyclerId;
	}

	public void setRecyclerId(Long recyclerId) {
		this.recyclerId = recyclerId;
	}

	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
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

	public Long getEstimatePrice() {
		return estimatePrice;
	}

	public void setEstimatePrice(Long estimatePrice) {
		this.estimatePrice = estimatePrice;
	}

	public Long getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Long realPrice) {
		this.realPrice = realPrice;
	}

	public Long getEstimatePoint() {
		return estimatePoint;
	}

	public void setEstimatePoint(Long estimatePoint) {
		this.estimatePoint = estimatePoint;
	}

	public Long getRealPoint() {
		return realPoint;
	}

	public void setRealPoint(Long realPoint) {
		this.realPoint = realPoint;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public String getInvalidReason() {
		return invalidReason;
	}

	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason == null ? null : invalidReason.trim();
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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