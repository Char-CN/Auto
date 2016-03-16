package org.blazer.dataextract.handle;

import org.blazer.common.util.ApplicationUtil;
import org.blazer.common.util.SourceUtil;
import org.blazer.common.util.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository(value = "AEHandle")
public class AEHandle {

	private static final Logger logger = LoggerFactory.getLogger(AEHandle.class);

	public static void main(String[] args) {
		logger.info(args.toString());
		String[] testArgs = new String[] { "1", "*" };
		ApplicationUtil.init(SourceUtil.resource + "logback_autoextract.xml");
		AEHandle handle = (AEHandle) ApplicationUtil.getBean("AEHandle");
		handle.handle(args, testArgs, SysConfig.test);
	}

	public void handle(String[] args, String[] testArgs, boolean isTest) {
		
	}

}
