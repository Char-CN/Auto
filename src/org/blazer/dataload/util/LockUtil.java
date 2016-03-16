package org.blazer.dataload.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 锁工具，支持windows和linux
 * 
 * @author heyunyang
 * 
 */
public class LockUtil {

	private static final Logger logger = LoggerFactory.getLogger(LockUtil.class);

	/**
	 * 当前进程ID
	 */
	public static String PROCESS_ID = null;

	/**
	 * 当前系统类型
	 */
	private static String system = "linux";

	private static String windowsCommand = "tasklist /fi \"pid eq ?\"";

	private static String linuxCommand = "ps -p ?";

	private static String command = "";

	static {
		PROCESS_ID = "";
		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
		String name = runtime.getName();
		PROCESS_ID = name.substring(0, name.indexOf("@"));

		system = System.getProperties().getProperty("os.name").toLowerCase();
		if (system.indexOf("windows") > -1) {
			system = "windows";
			command = windowsCommand;
		} else {
			system = "linux";
			command = linuxCommand;
		}
	}

	/**
	 * 判断PID是否正在执行
	 * 
	 * @param processID
	 * @return
	 */
	private static boolean isRunning(String processID) {
		boolean runningFlag = true;
		BufferedReader br = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(command.replace("?", processID));
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			int count = 0;
			while (br.readLine() != null) {
				count++;
			}
			runningFlag = count > 1;
		} catch (Exception e) {
			logger.error(system + " command error : ", e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
				}
			}
		}
		return runningFlag;
	}

	/**
	 * 根据文件读取pid判断是否锁住
	 * 
	 * @param file
	 * @return
	 */
	private static boolean isRunningByFile(File file) {
		if (!file.isFile()) {
			logger.info(file.getAbsolutePath() + " is not file");
			return false;
		}
		BufferedReader reader;
		String pid = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			pid = reader.readLine();
			reader.close();
		} catch (Exception e) {
			logger.error(system + " command error : ", e);
		}
		return isRunning(pid);
	}

	/**
	 * 锁
	 * 
	 * @param lockName
	 * @return true|false
	 * @throws IOException
	 */
	public static boolean lock(String lockName) throws IOException {
		File file = new File(lockName);
		// 正在执行或者不是文件
		if (LockUtil.isRunningByFile(file)) {
			logger.error("lock is fail");
			return false;
		}
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		fw.write(LockUtil.PROCESS_ID);
		fw.close();
		logger.info("lock is success");
		return true;
	}

	/**
	 * 解锁
	 * 
	 * @param lockName
	 */
	public static void unLock(String lockName) {
		try {
			File file = new File(lockName);
			if (file.isFile()) {
				file.delete();
			}
		} catch (Exception e) {
			logger.error("unlock fail");
			logger.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(system);
		System.out.println(PROCESS_ID);
		System.out.println(isRunning(PROCESS_ID));
		File file = new File("D://");
		System.out.println(file.isFile());
		System.out.println(file.exists());
	}

}
