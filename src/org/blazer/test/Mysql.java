package org.blazer.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mysql {

	public static void main(String[] args) throws Exception {
		Mysql mysql = new Mysql("com.mysql.jdbc.Driver", "jdbc:mysql://172.17.16.62:3306/DW_MetaData", "jkdc", "jkdc");
		List<HashMap<String, Object>> list = mysql.select("select * from AL_InputFile where Sort is ? and FileName=? and TypeId = ?", new Object[] {
				null, "外部商圈", 7 });
		for (HashMap<String, Object> map : list) {
			for (String key : map.keySet()) {
				System.out.print(map.get(key) + "\t");
			}
			System.out.println();
		}
		System.out.println(list.size());
		System.out.println();
		List<String> sqls = new ArrayList<String>();
		sqls.add("insert into test(name) values('a')");
		sqls.add("insert into test(name) values('b')");
		sqls.add("insert into test(name) values(#A#)");
		sqls.add("insert into test(name) values('d')");
		sqls.add("insert into test(name) values('e')");
		int[] ints = mysql.update(sqls);
		System.out.println(ints.length);

	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	String className;
	String url;
	String user;
	String password;
	Connection conn;
	PreparedStatement pt;

	public Mysql(String className, String url, String user, String password) throws SQLException {
		this.className = className;
		this.url = url;
		this.user = user;
		this.password = password;
		this.conn = DriverManager.getConnection(url, user, password);
		this.conn.setAutoCommit(false);
	}

	public Object selectUnique(String sql, Object... objects) throws SQLException {
		pt = conn.prepareStatement(sql);
		ResultSet rs = pt.getResultSet();
		if (!rs.next()) {
			throw new SQLException("sql [" + sql + "] not found record");
		}
		return rs.getObject(1);
	}

	public List<HashMap<String, Object>> select(String sql, Object... objects) throws SQLException {
		pt = conn.prepareStatement(sql);
		for (int i = 0; i < objects.length; i++) {
			pt.setObject(i + 1, objects[i]);
		}
		pt.execute();
		ResultSet rs = pt.getResultSet();
		ResultSetMetaData rsd = rs.getMetaData();
		String[] columnNames = new String[rsd.getColumnCount()];
		for (int i = 0; i < columnNames.length; i++) {
			columnNames[i] = rsd.getColumnLabel(i + 1);
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		while (rs.next()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			for (String name : columnNames) {
				map.put(name, rs.getObject(name));
			}
			list.add(map);
		}
		return list;
	}

	public int[] update(List<String> sqls) throws SQLException {
		conn.setAutoCommit(false);
		for (int i = 0; i < sqls.size(); i++) {
			if (i == 0) {
				pt = conn.prepareStatement(sqls.get(i));
				pt.addBatch();
				continue;
			}
			pt.addBatch(sqls.get(i));
		}
		int[] ints = null;
		try {
			ints = pt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			System.out.println(pt.getWarnings().getLocalizedMessage());
			e.printStackTrace();
		}
		return ints;
	}

	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		if (pt != null) {
			try {
				pt.close();
			} catch (SQLException e) {
			}
		}
	}

}
