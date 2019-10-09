package com.huishoucat.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class TWasteAttribute implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long wasteAttributeId;

    private Long wasteId;

    private Boolean isDeleted;

    private Date createTime;

    private Date updateTime;

    private String attributeContent;

    public Long getWasteAttributeId() {
        return wasteAttributeId;
    }

    public void setWasteAttributeId(Long wasteAttributeId) {
        this.wasteAttributeId = wasteAttributeId;
    }

    public Long getWasteId() {
        return wasteId;
    }

    public void setWasteId(Long wasteId) {
        this.wasteId = wasteId;
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

    public String getAttributeContent() {
        return attributeContent;
    }

    public void setAttributeContent(String attributeContent) {
        this.attributeContent = attributeContent == null ? null : attributeContent.trim();
    }
}