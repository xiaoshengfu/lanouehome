package com.huishoucat.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月18日 上午11:03:11
 * @version V1.0
 */
public class DateUtils {

	/**
	 * 判断日期是不是今天
	 * @param date
	 * @return 是返回true，不是返回false
	 */
	public static boolean isNowDay(Date date) {
		// 当前时间
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		// 获取今天的日期
		String nowDay = simpleDateFormat.format(nowDate);
		// 对比的时间
		String day = simpleDateFormat.format(date);
		return nowDay.equals(day);
	}

	/**
	 * 获取当前日期的起始时间
	 * @return 当前日期的起始时间
	 * @throws ParseException 
	 */
	public static Date getNowDay() throws ParseException {
		// 当前时间
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		// 获取今天的日期
		String nowDay = simpleDateFormat.format(nowDate);
		Date date = simpleDateFormat.parse(nowDay);
		return date;
	}

	/**
	 * 获得该月第一天 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException 
	 */
	public static Date getFirstDayOfMonth(int year, int month) throws ParseException {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayOfMonth = sdf.format(cal.getTime());
		return sdf.parse(firstDayOfMonth);
	}

	/**
	 * 获得该月最后一天 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException 
	 */
	public static Date getLastDayOfMonth(int year, int month) throws ParseException {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return sdf.parse(lastDayOfMonth);
	}
}
