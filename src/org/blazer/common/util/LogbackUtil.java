package org.blazer.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

public class LogbackUtil {

	private static final Logger logger = LoggerFactory.getLogger(LogbackUtil.class);

	public static void init() {
		init(SourceUtil.resource + "logback.xml");
	}

	public static void init(String logbackPath) {
		try {
			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
			loggerContext.reset();
			JoranConfigurator joranConfigurator = new JoranConfigurator();
			joranConfigurator.setContext(loggerContext);
			joranConfigurator.doConfigure(logbackPath);
			joranConfigurator.registerSafeConfiguration();
			logger.info("-- Logback Init Success");
		} catch (Exception e) {
			logger.error("-- Logback ERROR[{}] E[{}]", e.getMessage(), e);
		}
	}

}
