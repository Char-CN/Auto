package org.blazer.dataload.util;

import java.util.HashMap;

import org.blazer.common.util.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldUtil {

	private static final Logger logger = LoggerFactory.getLogger(FieldUtil.class);

	private static final int DEFAULT_SQL_PLACEHOLDER_SIZE = 50;
	public static final String PK_FIELD = "#PK#";
	public static final String UP_FIELD = "#UP#";

	public static void init() {
		// 调用该方法为了只是为了初始化该类静态变量
	}

	public static final HashMap<Integer, String> LINE = new HashMap<Integer, String>() {

		private static final long serialVersionUID = -3696850160403683359L;

		{
			int placeholder_size = DEFAULT_SQL_PLACEHOLDER_SIZE;
			try {
				placeholder_size = Integer.parseInt(SysConfig.get("default_sql_placeholder_size").toString());
			} catch (Exception e) {
				logger.error("read sys-config default_sql_placeholder_size error");
			}
			logger.info("system support line placeholder size : " + placeholder_size);
			for (int i = 1; i <= DEFAULT_SQL_PLACEHOLDER_SIZE; i++) {
				put(i, "{" + i + "}");
			}
		}

	};

	public static final HashMap<String, Integer> POINT = new HashMap<String, Integer>() {

		private static final long serialVersionUID = 5614534541955255932L;

		{
			int placeholder_size = DEFAULT_SQL_PLACEHOLDER_SIZE;
			try {
				placeholder_size = Integer.parseInt(SysConfig.get("default_sql_placeholder_size").toString());
			} catch (Exception e) {
				logger.error("read sys-config default_sql_placeholder_size error");
			}
			logger.info("system support point placeholder size : " + placeholder_size);
			for (int i = 1; i <= DEFAULT_SQL_PLACEHOLDER_SIZE; i++) {
				put("{" + i + "}", i);
			}
		}

	};

}
