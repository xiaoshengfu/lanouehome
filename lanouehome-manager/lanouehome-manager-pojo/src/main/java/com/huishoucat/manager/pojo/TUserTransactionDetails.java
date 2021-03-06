package com.huishoucat.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class TUserTransactionDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long transactionId;

	private Long userId;

	private Integer source;

	private Integer type;

	private Long num;

	private Date createTime;

	public TUserTransactionDetails() {
		super();
	}

	public TUserTransactionDetails(Long userId, Integer source, Integer type, Long num, Date createTime) {
		super();
		this.userId = userId;
		this.source = source;
		this.type = type;
		this.num = num;
		this.createTime = createTime;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}