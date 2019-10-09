package com.huishoucat.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class TWasteOrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private TWaste waste;

	private Long orderItemId;

	private Long wasteOrderId;

	private Long wasteId;

	private String description;

	private Long unitPoint;

	private Long attributePrice;

	private Long estimateUnitPrice;

	private Long realUnitPrice;

	private Integer estimateNum;

	private Integer realNum;

	private Integer unit;

	private Date createTime;

	private Date updateTime;

	public TWaste getWaste() {
		return waste;
	}

	public void setWaste(TWaste waste) {
		this.waste = waste;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getWasteOrderId() {
		return wasteOrderId;
	}

	public void setWasteOrderId(Long wasteOrderId) {
		this.wasteOrderId = wasteOrderId;
	}

	public Long getWasteId() {
		return wasteId;
	}

	public void setWasteId(Long wasteId) {
		this.wasteId = wasteId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Long getUnitPoint() {
		return unitPoint;
	}

	public void setUnitPoint(Long unitPoint) {
		this.unitPoint = unitPoint;
	}

	public Long getAttributePrice() {
		return attributePrice;
	}

	public void setAttributePrice(Long attributePrice) {
		this.attributePrice = attributePrice;
	}

	public Long getEstimateUnitPrice() {
		return estimateUnitPrice;
	}

	public void setEstimateUnitPrice(Long estimateUnitPrice) {
		this.estimateUnitPrice = estimateUnitPrice;
	}

	public Long getRealUnitPrice() {
		return realUnitPrice;
	}

	public void setRealUnitPrice(Long realUnitPrice) {
		this.realUnitPrice = realUnitPrice;
	}

	public Integer getEstimateNum() {
		return estimateNum;
	}

	public void setEstimateNum(Integer estimateNum) {
		this.estimateNum = estimateNum;
	}

	public Integer getRealNum() {
		return realNum;
	}

	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
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