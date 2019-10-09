package com.huishoucat.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class TUserBalance implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;

	private Long balance;

	private Long totalIncome;

	private Long totalOutlay;

	private Date createTime;

	private Date updateTime;

	public TUserBalance() {
		super();
	}

	public TUserBalance(Long userId, Date createTime, Date updateTime) {
		super();
		this.userId = userId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Long totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Long getTotalOutlay() {
		return totalOutlay;
	}

	public void setTotalOutlay(Long totalOutlay) {
		this.totalOutlay = totalOutlay;
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