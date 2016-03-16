package org.blazer.dataload.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvUtil {

	public static void main(String[] args) throws IOException {
		List<String[]> list = CsvUtil.readCsv("D:\\data\\bbb.csv");
		System.out.println(list.size());
	}

	private static final Logger logger = LoggerFactory.getLogger(CsvUtil.class);

	public static final String ENCODING = "UTF-8";

	public static final String DELIMITER = "\t";

	public static List<String[]> readCsv(String filePath) throws IOException {
		return readCsv(filePath, DELIMITER, ENCODING);
	}

	public static List<String[]> readCsv(String filePath, String delimiter) throws IOException {
		return readCsv(filePath, delimiter, ENCODING);
	}

	public static List<String[]> readCsv(String filePath, String delimiter, String encoding) throws IOException {
		List<String[]> list = new ArrayList<String[]>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String record = null;
			int intRow = 0;
			int intCol = 0;
			int intColTmp = 0;
			while ((record = br.readLine()) != null) {
				intRow++;
				// 过滤空行
				if (StringUtils.isNotEmpty(record)) {
					String[] strs = StringUtils.splitByWholeSeparatorPreserveAllTokens(record, String.valueOf(delimiter));
					intCol = strs.length;
					if (intColTmp != 0 && intCol != intColTmp) {
						logger.warn("Notice : this row column size != per row column size, row number : [" + intRow + "]");
					}
					intColTmp = intCol;
					list.add(strs);
				}
			}
			
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return list;
	}

}
