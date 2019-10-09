package com.huishoucat.common.pojo;

import java.io.Serializable;

/**
 * 蓝鸥e家消息对象
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月26日 下午5:11:16
 * @version V1.0
 */
public class HuishoucatMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	// 用户ID
	private Long userId;
	// 消息优先级
	private Integer level;
	// 消息内容
	private String message;

	public HuishoucatMessage() {
		super();
	}

	public HuishoucatMessage(Long userId, Integer level, String message) {
		super();
		this.userId = userId;
		this.level = level;
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
