package org.blazer.common.run;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.blazer.common.util.ClassPathHacker;
import org.blazer.common.util.Conf;
import org.blazer.common.util.ConfUtil;
import org.blazer.common.util.SourceUtil;
import org.blazer.common.util.StringUtil;

/**
 * 执行入口
 * 
 * @author heyunyang
 * 
 */
public class RunManager {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("==RunManager== " + "Usage: java -jar pkg.mainclass [parameters...]");
			System.exit(-1);
		}
		String pkgMainClass = convertPkgMainClassMapping(args[0]);
		try {
			Method mainMethod = Class.forName(pkgMainClass).getMethod("main", String[].class);
			// 加载第三方Driver
			addExtLib();
			// 调用
			mainMethod.invoke(null, (Object) StringUtil.removeIndex(args, 0));
		} catch (Exception e) {
			System.out.println("==RunManager== " + e.getMessage() + e);
		}
	}

	private static String convertPkgMainClassMapping(String pkgMainClass) {
		Conf conf = ConfUtil.getConf(SourceUtil.resource + "sys-pkg-register.properties");
		if (conf.containsKey(pkgMainClass)) {
			System.out.println(
					"==RunManager== " + "found pkg register : " + pkgMainClass + " to " + conf.get(pkgMainClass));
			pkgMainClass = conf.get(pkgMainClass);
		}
		return pkgMainClass;
	}

	private static void addExtLib() {
		try {
			String names[] = findFileNameRegexp(SourceUtil.root + "extlib", ".*[.]jar");
			for (int i = 0; i < names.length; i++) {
				System.out.println("==RunManager== " + "classpath add jar: " + SourceUtil.root + "extlib/" + names[i]);
				try {
					ClassPathHacker.addFile(SourceUtil.root + "extlib/" + names[i]);
				} catch (Exception e) {
					System.out.println("==RunManager== " + e.getMessage() + e);
				}
			}
		} catch (Exception e) {
			System.out.println("==RunManager== " + e.getMessage() + e);
		}
	}

	private static String[] findFileNameRegexp(String filePath, String regexp) {
		File directory = new File(filePath);
		if (!directory.isDirectory()) {
			return new String[0];
		}
		final Pattern p = Pattern.compile(regexp);
		String[] names = directory.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return p.matcher(name).matches();
			}
		});
		Arrays.sort(names);
		return names;
	}

}
