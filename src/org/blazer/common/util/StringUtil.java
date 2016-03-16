package org.blazer.common.util;

public class StringUtil {

	public static String[] removeIndex(String[] args, int index) {
		if (args == null || args.length == 0) {
			return null;
		} else if (args.length <= index) {
			return null;
		} else if (index == 0) {
			String[] retArgs = new String[args.length - 1];
			System.arraycopy(args, 1, retArgs, 0, retArgs.length);
			return retArgs;
		}
		String[] retArgs = new String[args.length - 1];
		int successIndex = 0;
		for (int i = 0; i < args.length; i++) {
			if (index != i) {
				retArgs[successIndex] = args[i];
				successIndex++;
			}
		}
		return retArgs;
	}

	public static String getString(Object o) {
		if (o == null)
			return null;
		return o.toString();
	}

	public static String trimAll(String str) {
		return str.replaceAll("\\s", "");
	}

	public static String union(Object... objs) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < objs.length; i++) {
			sb.append(objs[i]);
		}
		return sb.toString();
	}

	public static String toLog(String str, String... strs) {
		for (int i = 0; i < strs.length; i++) {
			str = str.replaceFirst("\\{\\%\\}", strs[i]);
		}
		return str;
	}

}
