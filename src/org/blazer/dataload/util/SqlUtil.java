package org.blazer.dataload.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.blazer.dataload.model.SQLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlUtil {

	private static final Logger logger = LoggerFactory.getLogger(SqlUtil.class);

	public static void main(String[] args) {

		// StringBuilder sb = new StringBuilder("insert into aaa
		// values(1,2,3)");
		//// int pos = sb.indexOf("{0}");
		//// sb.replace(pos, pos + 3, "asdasd");
		// sb.insert(23, "|AAAAA|");
		// sb.insert(25, "|BBB|");
		// System.out.println(sb);

		String sql = "insert into aaa values({1},{2},{3},{4},{5},{6},{7},{8},{9},{10},{DayKey},{HAH__AKey})";
		HashMap<String, String> columnMap = new HashMap<String, String>();
		columnMap.put("{1}", "'bb'");
		columnMap.put("{2}", "ccccc");
		columnMap.put("{3}", "ddd");
		columnMap.put("{4}", "eeee");
		columnMap.put("{5}", "asd");
		columnMap.put("{6}", "qqq");
		columnMap.put("{7}", "www");
		columnMap.put("{8}", "111");
		columnMap.put("{9}", "222");
		columnMap.put("{10}", "333333333333");
		columnMap.put("{DayKey}", "20160317");
		columnMap.put("{HAH__AKey}", "hyy");
		List<SQLParser> sqlParsers = placeholderMappingPoint(sql);
		System.out.println(placeholderReplaceConvertStr(sql, sqlParsers, columnMap));
	}

	public static List<List<SQLParser>> placeholderMappingPoints(final List<String> sqlList) {
		List<List<SQLParser>> sqlParserLists = new ArrayList<List<SQLParser>>();
		for (String sql : sqlList) {
			List<SQLParser> sqlParsers = placeholderMappingPoint(sql);
			sqlParserLists.add(sqlParsers);
		}
		return sqlParserLists;
	}

	public static List<SQLParser> placeholderMappingPoint(final String sql) {
		List<SQLParser> list = new ArrayList<SQLParser>();
		Pattern pattern = Pattern.compile("[{][0-9A-Za-z_]*[}]");
		Matcher matcher = pattern.matcher(sql);
		int minus = 0;
		while (matcher.find()) {
			SQLParser sqlParser = new SQLParser();
			sqlParser.setPlaceholder(matcher.group());
			sqlParser.setStart(matcher.start());
			sqlParser.setEnd(matcher.end());
			sqlParser.setMinusLength(minus);
			list.add(sqlParser);
			minus += sqlParser.getPlaceholder().length();
			logger.debug(matcher.group() + "|" + matcher.start() + "|" + matcher.end() + "|" + minus);
		}
		return list;
	}

	public static String placeholderReplaceConvertStr(final String sql, final List<SQLParser> sqlParsers, final HashMap<String, String> columnMap) {
		StringBuilder sb = new StringBuilder(sql);
		int add = 0;
		for (SQLParser sqlParser : sqlParsers) {
			String str = convertStr(columnMap.get(sqlParser.getPlaceholder()));
			sb.replace(sqlParser.getStart() + add - sqlParser.getMinusLength(), sqlParser.getEnd() + add - sqlParser.getMinusLength(), str);
			add += str.length();
		}
		return sb.toString();
	}

	public static String placeholderReplace(final String sql, final List<SQLParser> sqlParsers, final HashMap<String, String> columnMap) {
		StringBuilder sb = new StringBuilder(sql);
		int add = 0;
		for (SQLParser sqlParser : sqlParsers) {
			String str = "" + columnMap.get(sqlParser.getPlaceholder());
			sb.replace(sqlParser.getStart() + add - sqlParser.getMinusLength(), sqlParser.getEnd() + add - sqlParser.getMinusLength(), str);
			add += str.length();
		}
		return sb.toString();
	}

	/**
	 * 转换字符串
	 * 
	 * 该方法原存在于ALService里的私有方法。为提高效率废除replace（不允许直接替换系统占位符{0}），
	 * 因此ALService还有些地方没有修正过来。待以后处理。
	 */
	public static String convertStr(String str) {
		if (str == null) {
			return "null";
		}
		// 转义斜杠
		if (str.indexOf("\\") > -1) {
			str = str.replace("\\", "\\\\");
		}
		// 转义引号
		if (str.indexOf("'") > -1) {
			str = str.replace("'", "\\'");
		}
		return "'" + str + "'";
	}

}
