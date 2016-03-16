package org.blazer.dataextract.handle;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.blazer.common.util.ConsoleUtil;
import org.blazer.dataextract.tool.DBUtil;

@SuppressWarnings("unused")
public class DBConn {

	public static void main(String[] args) {

		// String s = "我\t你\r\naa aa";
		// System.out.println(s);
		// System.out.println();
		// System.out.println(s.replaceAll("\r|\n", ""));
		// System.out.println();
		// System.out.println(s.replaceAll("\r|\n", "").replaceAll("\t", " "));

		String strIn = null;
		for (;;) {
			System.out.println("请输入连接字符串:");
			System.out.println("  a.如(jdbc:mysql://127.0.0.1:3306/test,root,root)");
			System.out.println("  b.如(jdbc:sqlserver://127.0.0.1:1433;DatabaseName=dbname,sa,sa)");
			System.out.println("  c.如(jdbc:sybase:Tds:127.0.0.1:5007/dbname,sa,sa)");
			strIn = ConsoleUtil.SystemIn();
			if (strIn.equalsIgnoreCase("exit")) {
				break;
			}
			if (strIn.equalsIgnoreCase("help")) {
				help();
				continue;
			}
			if (strIn.equalsIgnoreCase("history")) {
				history();
				continue;
			}
			try {
				String strs[] = StringUtils.splitByWholeSeparatorPreserveAllTokens(strIn, ",");
				DBUtil db = new DBUtil(strs[0], strs[1], strs[2]);
				for (;;) {
					System.out.println("请输入查询语句:");
					strIn = ConsoleUtil.SystemIn();
					if (strIn.equalsIgnoreCase("exit")) {
						break;
					}
					if (strIn.equalsIgnoreCase("help")) {
						help();
						continue;
					}
					if (strIn.equalsIgnoreCase("history")) {
						history();
						continue;
					}
					try {
						db.selectOutPrint(System.out, strIn);
					} catch (Exception e) {
						e.printStackTrace();
						help();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				help();
			}
		}
	}

	public static void history() {
		System.out.println("==================历史记录==================");
		ConsoleUtil.last100();
		System.out.println("==========================================");
	}

	public static void help() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		System.out.println("====================帮助====================");
		System.out.println("1.exit    退出返回上一步");
		System.out.println("2.history 查看历史记录100条");
		System.out.println("==========================================");
	}

}
