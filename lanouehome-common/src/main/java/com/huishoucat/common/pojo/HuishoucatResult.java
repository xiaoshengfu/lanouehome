package com.huishoucat.common.pojo;

import java.io.Serializable;

/**
 * 蓝鸥e家全局返回对象
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月9日 下午3:03:18
 * @version V1.2
 */
public class HuishoucatResult implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 200;// 成功
	public static final int WARNING = 300;// 警告
	public static final int UNAUTH = 400;// 未认证
	public static final int ERROR = 500;// 错误

	private Integer stateCode;// 状态码
	private String message;// 错误消息
	private Object data;// 返回数据

	public HuishoucatResult(Integer stateCode, String message, Object data) {
		super();
		this.stateCode = stateCode;
		this.message = message;
		this.data = data;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 成功状态快速返回
	 * @param message
	 * @param data
	 * @return
	 */
	public static HuishoucatResult HuishoucatResultOK(String message, Object data) {
		return new HuishoucatResult(SUCCESS, message, data);
	}

	/**
	 * 警告状态快速返回
	 * @param message
	 * @param data
	 * @return
	 */
	public static HuishoucatResult HuishoucatResultWarning(String message, Object data) {
		return new HuishoucatResult(WARNING, message, data);
	}

	/**
	 * 未认证状态快速返回
	 * @param message
	 * @param data
	 * @return
	 */
	public static HuishoucatResult HuishoucatResultError(String message, Object data) {
		return new HuishoucatResult(ERROR, message, data);
	}

	/**
	 * 错误状态快速返回
	 * @param message
	 * @param data
	 * @return
	 */
	public static HuishoucatResult HuishoucatResultUnAuth(String message, Object data) {
		return new HuishoucatResult(UNAUTH, message, data);
	}

	/**
	 * 自定义快速返回
	 * @param stateCode
	 * @param message
	 * @param data
	 * @return
	 */
	public static HuishoucatResult HuishoucatResultBuild(Integer stateCode, String message, Object data) {
		return new HuishoucatResult(stateCode, message, data);
	}
}
