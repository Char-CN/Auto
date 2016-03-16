package org.blazer.common.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUtil {

	private static final String HIS_FILE_PATH = SourceUtil.root + "logs/commad.history";

	@SuppressWarnings("resource")
	public static String SystemIn() {
		System.out.print(">");
		String in = new Scanner(System.in).nextLine();
		try {
			File f = new File(HIS_FILE_PATH);
			if (!f.exists()) {
				if (f.getParentFile() != null && !f.exists()) {
					f.getParentFile().mkdirs();
				}
				f.createNewFile();
			}
			FileWriter fw = new FileWriter(f, true);
			fw.append(in + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return in;
	}

	public static void last100() {
		for (String str : readLast(100)) {
			System.out.println(str);
		}
	}

	private static List<String> readLast(long num) {
		File file = new File(HIS_FILE_PATH);
		// 定义结果集
		List<String> result = new ArrayList<String>();
		// 行数统计
		long count = 0;
		// 排除不可读状态
		if (!file.exists() || file.isDirectory() || !file.canRead()) {
			return null;
		}
		// 使用随机读取
		RandomAccessFile fileRead = null;
		try {
			// 使用读模式
			fileRead = new RandomAccessFile(file, "r");
			// 读取文件长度
			long length = fileRead.length();
			// 如果是0，代表是空文件，直接返回空结果
			if (length == 0L) {
				return result;
			} else {
				// 初始化游标
				long pos = length - 1;
				while (pos > 0) {
					pos--;
					// 开始读取
					fileRead.seek(pos);
					// 如果读取到\n代表是读取到一行
					if (fileRead.readByte() == '\n') {
						// 使用readLine获取当前行
						String line = fileRead.readLine();
						// 保存结果
						result.add(0, line);
						// 行数统计，如果到达了numRead指定的行数，就跳出循环
						count++;
						if (count == num) {
							break;
						}
					}
				}
				if (pos == 0) {
					fileRead.seek(0);
					result.add(0, fileRead.readLine());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileRead != null) {
				try {
					// 关闭资源
					fileRead.close();
				} catch (Exception e) {
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		last100();
	}

}
