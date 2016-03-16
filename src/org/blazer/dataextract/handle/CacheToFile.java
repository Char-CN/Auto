package org.blazer.dataextract.handle;

import org.apache.commons.lang.StringUtils;
import org.blazer.common.util.StringUtil;
import org.blazer.dataextract.tool.DBUtil;

public class CacheToFile {
	
	// url path sqls
	public static void main(String[] args) {
		String strs[] = StringUtils.splitByWholeSeparatorPreserveAllTokens(args[0], ",");
		DBUtil db = new DBUtil(strs[0], strs[1], strs[2]);

		String[] sqls = StringUtil.removeIndex(args, 0);
		sqls = StringUtil.removeIndex(sqls, 0);
		String sql = "";
		for (String str : sqls) {
			sql += str + " ";
		}

		try {
			db.selectCacheToFile(args[1], sql, "cp850", "gbk");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
