package com.huishoucat.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class TWaste implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer unit;

	private Long estimateUnitPrice;

	private Long wasteId;

	private Long parentId;

	private String name;

	private Integer level;

	private Long unitPoint;

	private Boolean isParent;

	private Boolean isDeleted;

	private String pictureUrl;

	private Integer sort;

	private Date createTime;

	private Date updateTime;

	private String description;

	public TWaste() {
		super();
	}

	public TWaste(Long wasteId, String pictureUrl) {
		super();
		this.wasteId = wasteId;
		this.pictureUrl = pictureUrl;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Long getEstimateUnitPrice() {
		return estimateUnitPrice;
	}

	public void setEstimateUnitPrice(Long estimateUnitPrice) {
		this.estimateUnitPrice = estimateUnitPrice;
	}

	public Long getWasteId() {
		return wasteId;
	}

	public void setWasteId(Long wasteId) {
		this.wasteId = wasteId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getUnitPoint() {
		return unitPoint;
	}

	public void setUnitPoint(Long unitPoint) {
		this.unitPoint = unitPoint;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}