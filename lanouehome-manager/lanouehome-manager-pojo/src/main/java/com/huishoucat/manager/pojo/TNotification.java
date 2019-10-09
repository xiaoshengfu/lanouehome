package com.huishoucat.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class TNotification implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long notificationId;

	private Long userId;

	private String notificationContent;

	private Integer level;

	private Boolean isRead;

	private Boolean isDelete;

	private Date createTime;

	private Date updateTime;

	public TNotification() {
		super();
	}

	public TNotification(Boolean isRead, Date updateTime) {
		super();
		this.isRead = isRead;
		this.updateTime = updateTime;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNotificationContent() {
		return notificationContent;
	}

	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent == null ? null : notificationContent.trim();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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