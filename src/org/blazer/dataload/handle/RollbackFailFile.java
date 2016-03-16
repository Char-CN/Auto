package org.blazer.dataload.handle;

import java.util.HashMap;
import java.util.Map;

import org.blazer.common.dao.JDBCDao;
import org.blazer.common.util.ApplicationUtil;
import org.blazer.common.util.SourceUtil;
import org.blazer.common.util.StringUtil;
import org.blazer.common.util.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将失败或成功的文件回滚到上一级目录
 * 
 * @author heyunyang
 * 
 */
public class RollbackFailFile {

	private static final Logger logger = LoggerFactory.getLogger(RollbackFailFile.class);

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		if (SysConfig.test) {
			args = new String[] { "20150101", "20161231", "*", "*", "fail" };
		}
		String dateBegin = args[0];
		String dateEnd = args[1];
		String groupIDs = args[2];
		String fileIds = args[3];
		String result = "fail";
		if (args.length > 4) {
			result = args[4];
		}
		ApplicationUtil.init(SourceUtil.resource + "logback_autoload.xml");
		JDBCDao jdbcDao = (JDBCDao) ApplicationUtil.getBean("JDBCDao");
		StringBuilder sb = new StringBuilder("");
		sb.append("SELECT `log`.RealFilePath, `log`.RealFileName, `file`.FilePath");
		sb.append(" FROM AL_InputFileLog `log`");
		sb.append(" INNER JOIN AL_InputGroup `group` ON `log`.GroupID=`group`.RecordID");
		sb.append(" INNER JOIN AL_InputFile `file` ON `log`.FileID=`file`.RecordID");
		sb.append(" WHERE 1=1");
		sb.append(" AND DATE_FORMAT(`log`.CTime,'%Y%m%d')>='" + dateBegin + "'");
		sb.append(" AND DATE_FORMAT(`log`.CTime,'%Y%m%d')<='" + dateEnd + "'");
		sb.append(" AND Result='" + result + "'");

		if (!groupIDs.equalsIgnoreCase("*")) {
			String selectGroup = "";
			if (groupIDs.toLowerCase().startsWith("notin")) {
				selectGroup = "NOT IN";
				groupIDs = groupIDs.substring(5);
			} else if (groupIDs.toLowerCase().startsWith("in")) {
				selectGroup = "IN";
				groupIDs = groupIDs.substring(2);
			} else {
				selectGroup = "IN";
			}
			sb.append(" AND `group`.RecordID ").append(selectGroup).append("(").append(groupIDs).append(")");
		}
		if (!fileIds.equalsIgnoreCase("*")) {
			String selectGroup = "";
			if (fileIds.toLowerCase().startsWith("notin")) {
				selectGroup = "NOT IN";
				fileIds = fileIds.substring(5);
			} else if (fileIds.toLowerCase().startsWith("in")) {
				selectGroup = "IN";
				fileIds = fileIds.substring(2);
			} else {
				selectGroup = "IN";
			}
			sb.append(" AND `file`.RecordID ").append(selectGroup).append("(").append(fileIds).append(")");
		}

		logger.info("SQL : " + sb);
		HashMap<String, String> historyMap = new HashMap<String, String>();
		Runtime runtime = Runtime.getRuntime();
		Process p;
		for (Map<String, Object> map : jdbcDao.find(sb.toString())) {
			String filePath = StringUtil.getString(map.get("FilePath"));
			try {
				if (!filePath.endsWith("/")) {
					filePath += "/";
				}
				String cmd = "mv " + filePath + result + "/*" + dateBegin + "* " + filePath;
				if (historyMap.containsKey(cmd)) {
					continue;
				}
				historyMap.put(cmd, cmd);
				logger.info("cmd : " + cmd);
//				p = runtime.exec(cmd);
//				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
//				br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
//				String t = null;
//				while ((t = br.readLine()) != null) {
//					logger.info(t);
//				}
//				br.close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("e", e);
			}
		}

	}

}
