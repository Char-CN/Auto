package org.blazer.dataload.util;

import java.util.HashMap;

import org.blazer.common.util.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldUtil {

	private static final Logger logger = LoggerFactory.getLogger(FieldUtil.class);

	private static int DEFAULT_SQL_PLACEHOLDER_SIZE = 50;
	public static final String PK_FIELD = "#PK#";
	public static final String UP_FIELD = "#UP#";
	public static final String MATCH_BEGIN = "{";
	public static final String MATCH_END = "}";

	public static void main(String[] args) {
		System.out.println(FieldUtil.LINE.get(50));
		System.out.println(FieldUtil.LINE.get(52));
	}

	public static void init() {
		// 调用该方法为了只是为了初始化该类静态变量
	}

	public static int getDefaultSqlPlaceholderSize() {
		return DEFAULT_SQL_PLACEHOLDER_SIZE;
	}

	static {
		try {
			DEFAULT_SQL_PLACEHOLDER_SIZE = Integer.parseInt(SysConfig.get("default_sql_placeholder_size").toString());
		} catch (Exception e) {
			logger.error("read sys-config default_sql_placeholder_size error");
		}
		logger.info("system support line placeholder size : " + DEFAULT_SQL_PLACEHOLDER_SIZE);
	}

	public static final HashMap<Integer, String> LINE = new HashMap<Integer, String>() {

		private static final long serialVersionUID = -3696850160403683359L;

		{
			for (int i = 1; i <= DEFAULT_SQL_PLACEHOLDER_SIZE; i++) {
				put(i, MATCH_BEGIN + i + MATCH_END);
			}
		}

	};

	public static final HashMap<String, Integer> POINT = new HashMap<String, Integer>() {

		private static final long serialVersionUID = 5614534541955255932L;

		{
			for (int i = 1; i <= DEFAULT_SQL_PLACEHOLDER_SIZE; i++) {
				put(MATCH_BEGIN + i + MATCH_END, i);
			}
		}

	};

}
