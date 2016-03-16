package org.blazer.dataload.util;

import java.util.ArrayList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.blazer.common.util.DateUtil;

public class StringUtil extends org.blazer.common.util.StringUtil {

	public static String findOneStrByReg(final String str, final String reg) {
		try {
			return findStrByReg(str, reg).get(0);
		} catch (IndexOutOfBoundsException e) {
		}
		return null;
	}

	public static List<String> findStrByReg(final String str, final String reg) {
		List<String> list = new ArrayList<String>();
		if (str == null || reg == null) {
			return list;
		}
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		while (m.find()) {
			for (int i = 1; i <= m.groupCount(); i++) {
				list.add(m.group(i));
			}
		}
		return list;
	}

	/**
	 * 
	 * @param str
	 * 
	 * @param reg
	 *            正则表达式,用()表示抓取的组
	 * @param customStr
	 *            ${x} : 表示抓取的第x个组替换进来, ${.}表示所有抓取组的内容依次替换
	 * @return
	 */
	public static String replaceAllByReg(final String str, final String reg, String customStr) {
		if (str == null || reg == null || customStr == null) {
			return str;
		}
		// 兼容支持yyyy-MM-dd的日期转换
		if (reg.indexOf("yy") > -1 && reg.indexOf("MM") > -1) {
			return DateUtil.format(DateUtil.format(str, reg), customStr);
		}
		List<String> list = findStrByReg(str, reg);
		boolean bol = customStr.equals("{.}");
		if (customStr.indexOf("{.}") != -1) {
			String retStr = "";
			for (int i = 0; i < list.size(); i++) {
				retStr += list.get(i);
			}
			if (retStr.equals(StringUtils.EMPTY)) {
				return customStr;
			}
			customStr = customStr.replaceAll("\\{\\.\\}", retStr);
			if (bol) {
				return customStr;
			}
		}
		String retStr = customStr;
		// 从{1}开始
		for (int i = 0; i < list.size(); i++) {
			retStr = retStr.replaceAll("(?i)[{][" + (i + 1) + "][}]", list.get(i));
		}
		return retStr;
	}

	public static void main(String[] args) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\w+(\\d{2})\\w+");
		Matcher m = p.matcher("qweqew34sdsa12sd");
		while (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println(java.util.regex.Pattern.compile("\\w+(\\d{2})\\w+").matcher("qweqew34sdsa12sd").find());
		// System.out.println("---------------------");
		// String str = "sdsaqsq2012-12-22ssasq";
		// String str1 = "2012-12-22";
		// String reg1 = "\\d{4}-\\d{2}-\\d{2}";
		// System.out.println(str.matches(reg1));
		// String rep2 = "\\w*(\\d{4})-(\\d{2})-(\\d{2})\\w*";
		// System.out.println(str.matches(rep2));
		// System.out.println(getRegexpValue(str, rep2));
		// System.out.println(str);
		// System.out.println(str1.replaceAll("\\d{4}-\\d{2}-\\d{2}",
		// getRegexpValue(str, rep2)));
		System.out.println("asdasd#A# #B#".replaceAll("#A#", "{0}"));
		System.out.println(replaceAllByReg("20121222", "\\d{4}\\d{2}\\d{2}", "{0}-{1}-{2}"));
		System.out.println(replaceAllByReg("20121222", "(\\d{4})(\\d{2})(\\d{2})", "{0}-{1}-{1}"));
		System.out.println(replaceAllByReg("2012-12-22", "(\\d{4})-(\\d{2})-(\\d{2})", "{.}"));
		System.out.println(replaceAllByReg("sdsaqsq2012-12-22ssasq", "(\\w*)(\\d{4})-(\\d{2})-(\\d{2})", "^\\w*(\\d{4})-(\\d{2})-(\\d{2})\\w*$"));
		System.out.println(replaceAllByReg("sds2d中国人s1ds", "(\\W)", "#A#_{.}"));
		System.out.println(replaceAllByReg("sasd12", "(.*)", "asdasd{.}"));
		System.out.println(replaceAllByReg("", "", "#E#_#F#_#G#"));
	}

}
