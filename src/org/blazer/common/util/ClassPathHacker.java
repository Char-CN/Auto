package org.blazer.common.util;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassPathHacker {

	@SuppressWarnings("rawtypes")
	private static final Class[] parameters = new Class[] { URL.class };

	public static void addFile(String s) throws Exception {
		File f = new File(s);
		addFile(f);
	}

	public static void addFile(File f) throws Exception {
		addURL(f.toURI().toURL());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void addURL(URL u) throws Exception {
		URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		Class sysclass = URLClassLoader.class;
		try {
			Method method = sysclass.getDeclaredMethod("addURL", parameters);
			method.setAccessible(true);
			method.invoke(sysloader, new Object[] { u });
		} catch (Exception e) {
			throw e;
		}
	}

}