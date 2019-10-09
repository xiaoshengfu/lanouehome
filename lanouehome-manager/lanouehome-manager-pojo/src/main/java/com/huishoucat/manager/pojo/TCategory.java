package com.huishoucat.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class TCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long categoryId;

	private Long parentId;

	private String name;

	private Integer state;

	private Integer sortOrder;

	private Boolean isParent;

	private Boolean isDeleted;

	private Date createTime;

	private Date updateTime;

	public TCategory() {
		super();
	}

	public TCategory(Long categoryId, Boolean isParent, Date updateTime) {
		super();
		this.categoryId = categoryId;
		this.isParent = isParent;
		this.updateTime = updateTime;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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