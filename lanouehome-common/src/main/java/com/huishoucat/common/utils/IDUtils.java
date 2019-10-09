package com.huishoucat.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * ID生成工具类
 * 
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月3日 下午5:53:44
 * @version V1.0
 */
public class IDUtils {

	/**
	 * 随机数生成6位数验证码
	 * @return
	 */
	public static String getVerificationCode() {
		long first3 = System.currentTimeMillis() % 1000;
		Random random = new Random();
		int end3 = random.nextInt(999);
		return String.format("%03d", first3) + String.format("%03d", end3);
	}

	/**
	 * 生成不带“-”的UUID
	 * @return
	 */
	public static String getUUID() {
		StringBuilder newUuid = new StringBuilder();
		String oldUuid = UUID.randomUUID().toString();
		String[] strings = oldUuid.split("-");
		for (int i = 0; i < strings.length; i++) {
			newUuid.append(strings[i]);
		}
		return newUuid.toString();
	}

	/**
	 * 生成itemId
	 * @return
	 */
	public static Long getItemId() {
		long time = System.currentTimeMillis();
		Random random = new Random();
		int end2 = random.nextInt(99);
		return new Long(time + String.format("%02d", end2));
	}
}
