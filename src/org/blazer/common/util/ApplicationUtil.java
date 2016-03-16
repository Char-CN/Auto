package org.blazer.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationUtil {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationUtil.class);

	private static ApplicationContext ctx = null;

	public static void init() {
		init(SourceUtil.resource + "logback.xml");
	}

	public static void init(String logbackPath) {
		long l1 = System.currentTimeMillis();
		logger.info("----Begin----------------------------------------");
		logger.info("-- param logbackPath : " + logbackPath);
		LogbackUtil.init(logbackPath);
		if (ctx != null) {
			long l2 = System.currentTimeMillis();
			logger.info("-- ctx is not null");
			logger.info("-- method waste time : " + (l2 - l1));
			logger.info("----End--------------------------------------------");
			return;
		}
		try {
			String applicationPath = "file:" + SourceUtil.resource + "applicationContext.xml";
			logger.info("-- param applicationPath : " + applicationPath);
			ApplicationUtil.ctx = new FileSystemXmlApplicationContext(applicationPath);
			logger.info("-- Application Init SUCCESS 加载Spring应用成功");
		} catch (Exception e) {
			logger.error("-- Application Init ERROR[{}] E[{}]", e.getMessage(), e);
		}
		long l2 = System.currentTimeMillis();
		logger.info("-- method waste time            : " + (l2 - l1));
		logger.info("----End--------------------------------------------");
	}

	public static void setCtx(ApplicationContext ctx) {
		ApplicationUtil.ctx = ctx;
	}

	public static Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}

}
