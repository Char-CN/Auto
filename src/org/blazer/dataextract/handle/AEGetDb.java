package org.blazer.dataextract.handle;

import org.blazer.common.util.ApplicationUtil;
import org.blazer.common.util.SourceUtil;
import org.blazer.common.util.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository(value = "AEGetDb")
public class AEGetDb {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AEGetDb.class);

	public static void main(String[] args) {
		String[] testArgs = new String[] { "1", "*" };
		ApplicationUtil.init(SourceUtil.resource + "logback_autoextract.xml");
		AEGetDb handle = (AEGetDb) ApplicationUtil.getBean("AEGetDb");
		// testArgs = args;
		handle.handle(args, testArgs, SysConfig.test);
	}

	public void handle(String[] args, String[] testArgs, boolean isTest) {
		
	}

}
