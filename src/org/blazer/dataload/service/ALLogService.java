package org.blazer.dataload.service;

import org.blazer.common.dao.JDBCDao;
import org.blazer.common.util.SysConfig;
import org.blazer.dataload.model.ALInputFileBean;
import org.blazer.dataload.model.FileVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 记录日志，日志若写到数据库对应AL_InputFileLog表，如果数据量极大请注意清理
 * 
 * @author heyunyang
 * 
 */
@Repository(value = "ALLogService")
public class ALLogService {

	private static final Logger logger = LoggerFactory.getLogger(ALLogService.class);

	@Autowired
	private JDBCDao jdbcDao;

	/**
	 * 记录错误日志
	 * 
	 * @param aif
	 * @param e
	 */
	public void failLog(ALInputFileBean aif, Exception e) {
		if (!SysConfig.databaseLogSuccessFlag) {
			return;
		}
		StringBuilder sb = new StringBuilder(e.toString());
		for (StackTraceElement ste : e.getStackTrace()) {
			sb.append("\r\n    at ").append(ste.toString());
		}
		log(aif, "fail", sb.toString());
	}

	/**
	 * 记录正确日志
	 * 
	 * @param aif
	 */
	public void successLog(ALInputFileBean aif) {
		if (!SysConfig.databaseLogSuccessFlag) {
			return;
		}
		log(aif, "success", "");
	}

	private void log(ALInputFileBean aif, String result, String detail) {
		try {
			String sql = "INSERT INTO AL_InputFileLog(GroupID, FileID, RealFilePath, RealFileName, Result, Detail) ";
			sql += " VALUES(?,?,?,?,?,?)";
			Object[] objs = new Object[6];
			objs[0] = aif.getGroupId();
			objs[1] = aif.getRecordId();
			objs[2] = aif.getCurrentFileVar().get(FileVariable.FILEPATH);
			objs[3] = aif.getCurrentFileVar().get(FileVariable.FILENAME);
			objs[4] = result;
			objs[5] = detail;
			jdbcDao.update(sql, objs);
		} catch (Exception e) {
			logger.error("Write log to AL_InputFileLog fail : " + e.getMessage(), e);
		}
	}

}
