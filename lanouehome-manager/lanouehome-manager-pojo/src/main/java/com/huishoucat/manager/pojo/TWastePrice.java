package com.huishoucat.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class TWastePrice implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long wastePriceId;

	private Long wasteId;

	private Long priceCeiling;

	private Long priceFloor;

	private Integer unit;

	private Boolean isDeleted;

	private Date createTime;

	private Date updateTime;

	public Long getWastePriceId() {
		return wastePriceId;
	}

	public void setWastePriceId(Long wastePriceId) {
		this.wastePriceId = wastePriceId;
	}

	public Long getWasteId() {
		return wasteId;
	}

	public void setWasteId(Long wasteId) {
		this.wasteId = wasteId;
	}

	public Long getPriceCeiling() {
		return priceCeiling;
	}

	public void setPriceCeiling(Long priceCeiling) {
		this.priceCeiling = priceCeiling;
	}

	public Long getPriceFloor() {
		return priceFloor;
	}

	public void setPriceFloor(Long priceFloor) {
		this.priceFloor = priceFloor;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
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