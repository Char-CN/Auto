package org.blazer.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static final String PATTERN = "yyyy-MM-dd";

	private static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat(PATTERN);

	public static String subDay(String date, Integer number) throws ParseException {
		return sub(date, number, Calendar.DAY_OF_MONTH);
	}

	public static String subMonth(String date, Integer number) throws ParseException {
		return sub(date, number, Calendar.MONTH);
	}

	public static String subYear(String date, Integer number) throws ParseException {
		return sub(date, number, Calendar.YEAR);
	}

	public static String sub(String date, Integer number, Integer field) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(yyyy_MM_dd.parse(date));
		c.add(field, -number);
		return yyyy_MM_dd.format(c.getTime());
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date) {
		return format(date, PATTERN);
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "null";
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = PATTERN;
		}
		return new java.text.SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return 日期类型
	 */
	public static Date format(String date) {
		return format(date, null);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式
	 * @return 日期类型
	 */
	public static Date format(String date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = PATTERN;
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return new Date();
		}
		Date d = null;
		try {
			d = new java.text.SimpleDateFormat(pattern).parse(date);
		} catch (ParseException pe) {
		}
		return d;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(subMonth("2013-12-12", 12));
		System.out.println(DateUtil.format(DateUtil.format("2013-12-12", "yyyy-MM-dd"), "yyyyMMdd"));
	}

}
