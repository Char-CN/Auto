package org.blazer.dataload.handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MoveFile {

	public static void main(String[] args) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		Process p = runtime.exec("mv /Users/hyy/Work/data/fail/hyy.csv /Users/hyy/Work/data");
//		Process p = runtime.exec("ps -ef|grep java");
		int i = p.waitFor();
		String t = null;
		if (i == 0) {
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
			while ((t = br.readLine()) != null) {
				System.out.println(t);
			}
			br.close();
		} else {
			BufferedReader br2 = new BufferedReader(new InputStreamReader(p.getErrorStream(), "UTF-8"));
			while ((t = br2.readLine()) != null) {
				System.out.println(t);
			}
			br2.close();
		}
	}

}
