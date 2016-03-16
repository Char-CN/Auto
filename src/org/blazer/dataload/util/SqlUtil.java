package org.blazer.dataload.util;

public class SqlUtil {

	public static void main(String[] args) {
	
		StringBuilder sb = new StringBuilder("insert into aaa values(1,2,3)");
//		int pos = sb.indexOf("{0}");
//		sb.replace(pos, pos + 3, "asdasd");
		sb.insert(23, "|AAAAA|");
		sb.insert(25, "|BBB|");
		System.out.println(sb);
	}

}
