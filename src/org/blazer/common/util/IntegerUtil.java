package org.blazer.common.util;

public class IntegerUtil {

	public static Integer getInteger(Object i) {
		if (i == null) {
			return null;
		}
		return Integer.parseInt(i.toString());
	}

}
