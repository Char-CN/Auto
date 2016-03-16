package org.blazer.dataextract.tool;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.blazer.common.util.StringUtil;

public class DBUtil {

	static void insertTest() throws SQLException {
		DBUtil dbUtil = new DBUtil("jdbc:mysql://127.0.0.1:3306/MyDB", "root", "root");
		String name = "hello_hyy_name_";
		String pass = "hello_hyy_pass_";
		String idcard = "";
		for (int i = 0; i < 1000; i++) {
			idcard += i % 2 == 0 ? 0 : 1;
		}
		idcard += "_";
		String remark = "";
		for (int i = 0; i < 10000; i++) {
			remark += i % 2 == 0 ? 0 : 1;
		}
		remark += "_";
		long l1 = System.currentTimeMillis();
		for (int i = 100000; i < 1000000; i++) {
			String name_ = name + i;
			String pass_ = pass + i;
			String idcard_ = idcard + i;
			String remark_ = remark + i;
			dbUtil.update("insert into MyDB.BigTable(UserName,Password,age,IDCard,Remark) values('" + name_ + "','" + pass_ + "','" + i + "','" + idcard_
					+ "','" + remark_ + "')");
		}
		System.out.println("wasrer time : " + (System.currentTimeMillis() - l1));
	}

	static void cacheTest() throws Exception {
		DBUtil dbUtil = new DBUtil("jdbc:mysql://127.0.0.1:3306/MyDB", "root", "root");
		long l1 = System.currentTimeMillis();
		dbUtil.selectCacheToFile("/Users/hyy/Work/data/select.cache", "select * from BigTable");
		System.out.println("wasrer time : " + (System.currentTimeMillis() - l1));
		Thread.sleep(10 * 1000);
		System.out.println("over!");
	}

	static void tabsTest() throws Exception {
		DBUtil dbUtil = new DBUtil("jdbc:mysql://127.0.0.1:3306/MyDB", "root", "root");
		long l1 = System.currentTimeMillis();
		dbUtil.selectCacheToFile("/Users/hyy/Work/data/select.cache", "select * from BigTable");
		System.out.println("wasrer time : " + (System.currentTimeMillis() - l1));
		Thread.sleep(10 * 1000);
		System.out.println("over!");
	}

	public static void main(String[] args) throws Exception {
		insertTest();
	}

	private Connection conn = null;
	private PreparedStatement ps = null;
	private String url = null;
	private String userName = null;
	private String password = null;

	public DBUtil(String url, String userName, String password) {
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void spawnConnection() throws SQLException {
		this.conn = DriverManager.getConnection(this.url, this.userName, this.password);
	}

	public void spawnPreparedStatement(String sql) throws SQLException {
		this.ps = this.conn.prepareStatement(sql);
	}

	public HashMap<Object, Object> selectUnique(String sql, Object... objs) throws SQLException {
		List<HashMap<Object, Object>> list = select(sql, objs);
		return list.size() == 0 ? new HashMap<Object, Object>() : list.get(0);
	}

	public HashMap<Object, Object> selectUniqueNoIndex(String sql, Object... objs) throws SQLException {
		List<HashMap<Object, Object>> list = selectNoIndex(sql, objs);
		return list.size() == 0 ? new HashMap<Object, Object>() : list.get(0);
	}

	public List<HashMap<Object, Object>> select(String sql, Object... objs) throws SQLException {
		List<HashMap<Object, Object>> list = new ArrayList<HashMap<Object, Object>>();
		spawnConnection();
		spawnPreparedStatement(sql);
		bindParams(objs);
		ResultSet rs = this.ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		String[] columnNames = new String[rsmd.getColumnCount()];
		for (int i = 0; i < columnNames.length; i++) {
			columnNames[i] = rsmd.getColumnLabel(i + 1);
		}
		while (rs.next()) {
			HashMap<Object, Object> columnMap = new HashMap<Object, Object>();
			for (int i = 0; i < columnNames.length; i++) {
				columnMap.put(columnNames[i], rs.getObject(columnNames[i]));
				columnMap.put(i, rs.getObject(i + 1));
			}
			list.add(columnMap);
		}
		close();
		return list;
	}

	public List<HashMap<Object, Object>> selectNoIndex(String sql, Object... objs) throws SQLException {
		List<HashMap<Object, Object>> list = new ArrayList<HashMap<Object, Object>>();
		spawnConnection();
		spawnPreparedStatement(sql);
		bindParams(objs);
		ResultSet rs = this.ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		String[] columnNames = new String[rsmd.getColumnCount()];
		for (int i = 0; i < columnNames.length; i++) {
			columnNames[i] = rsmd.getColumnLabel(i + 1);
		}
		while (rs.next()) {
			HashMap<Object, Object> columnMap = new HashMap<Object, Object>();
			for (int i = 0; i < columnNames.length; i++) {
				columnMap.put(columnNames[i], rs.getObject(columnNames[i]));
			}
			list.add(columnMap);
		}
		close();
		return list;
	}

	public void selectOutPrint(PrintStream ps, String sql, Object... objs) throws SQLException, IOException {
		spawnConnection();
		spawnPreparedStatement(sql);
		bindParams(objs);
		ResultSet rs = this.ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		String[] columnNames = new String[rsmd.getColumnCount()];
		for (int i = 0; i < columnNames.length; i++) {
			columnNames[i] = rsmd.getColumnLabel(i + 1);
			ps.print(columnNames[i]);
			if (i != columnNames.length - 1) {
				ps.print("\t");
			}
		}
		ps.println();
		while (rs.next()) {
			String row = "";
			for (int i = 0; i < columnNames.length; i++) {
				row += rs.getObject(columnNames[i]);
				// row = new String(row.getBytes("cp852"),"gbk");
				if (i != columnNames.length - 1) {
					row += "\t";
				}
			}
			ps.println(row);
		}
		close();
	}

	public void selectCacheToFile(String filePath, String sql, Object... objs) throws SQLException, IOException {
		FileWriter fw = new FileWriter(filePath);
		spawnConnection();
		spawnPreparedStatement(sql);
		bindParams(objs);
		ResultSet rs = this.ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		String[] columnNames = new String[rsmd.getColumnCount()];
		for (int i = 0; i < columnNames.length; i++) {
			columnNames[i] = rsmd.getColumnLabel(i + 1);
		}
		while (rs.next()) {
			String row = "";
			for (int i = 0; i < columnNames.length; i++) {
				row += rs.getObject(columnNames[i]);
				if (i != columnNames.length - 1) {
					row += "\t";
				}
			}
			fw.write(row + "\n");
		}
		fw.close();
		close();
	}

	public void selectCacheToFile(String filePath, String sql, String oldEncoding, String newEncoding, Object... objs) throws SQLException, IOException {
		FileWriter fw = new FileWriter(filePath);
		spawnConnection();
		spawnPreparedStatement(sql);
		bindParams(objs);
		ResultSet rs = this.ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		String[] columnNames = new String[rsmd.getColumnCount()];
		for (int i = 0; i < columnNames.length; i++) {
			columnNames[i] = rsmd.getColumnLabel(i + 1);
		}
		while (rs.next()) {
			String row = "";
			for (int i = 0; i < columnNames.length; i++) {
				Object rst = rs.getObject(columnNames[i]);
				if (rst != null) {
					row += new String(rst.toString().getBytes(oldEncoding), newEncoding);
				} else {
					row += rst;
				}
				if (i != columnNames.length - 1) {
					row += "\t";
				}
			}
			fw.write(row + "\n");
		}
		fw.close();
		close();
	}

	public void batchUpdateIgnoreError(List<String> sqls) throws SQLException {
		if (sqls == null || sqls.size() == 0) {
			return;
		}
		spawnConnection();
		this.conn.setAutoCommit(false);
		spawnPreparedStatement(sqls.get(0));
		try {
			this.ps.executeUpdate(sqls.get(0));
		} catch (SQLException e) {
		}
		for (int i = 1; i < sqls.size(); i++) {
			try {
				this.ps.executeUpdate(sqls.get(i));
			} catch (SQLException e) {
			}
		}
		this.conn.commit();
		close();
	}

	public void batchUpdate(List<String> sqls) throws SQLException {
		int errorRow = 0;
		String errorSql = null;
		try {
			if (sqls == null || sqls.size() == 0) {
				return;
			}
			spawnConnection();
			this.conn.setAutoCommit(false);
			errorSql = sqls.get(0);
			spawnPreparedStatement(sqls.get(0));
			this.ps.executeUpdate(sqls.get(0));
			for (int i = 1; i < sqls.size(); i++) {
				errorRow = i;
				errorSql = sqls.get(i);
				this.ps.executeUpdate(sqls.get(i));
			}
			this.conn.commit();
			close();
		} catch (SQLException e) {
			throw new SQLException("error row : " + errorRow + ", error sql : " + errorSql, e);
		}
	}

	public int insert(String sql, Object... objs) throws SQLException {
		return update(sql, objs);
	}

	public int delete(String sql, Object... objs) throws SQLException {
		return update(sql, objs);
	}

	public int update(String sql, Object... objs) throws SQLException {
		int num = 0;
		try {
			spawnConnection();
			this.conn.setAutoCommit(false);
			spawnPreparedStatement(sql);
			bindParams(objs);
			num = this.ps.executeUpdate();
			this.conn.commit();
			close();
		} catch (SQLException e) {
			throw new SQLException(
					StringUtil.union("error row : 0, error sql : ", sql, " parameter : ") + StringUtil.union(objs), e);
		}
		return num;
	}

	public int updateNoSpawnConn(String sql, Object... objs) throws SQLException {
		int num = 0;
		try {
			bindParams(objs);
			num = this.ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(
					StringUtil.union("error row : 0, error sql : ", sql, " parameter : ") + StringUtil.union(objs), e);
		}
		return num;
	}

	public Connection getConn() {
		return this.conn;
	}

	public void execute(String sql, Object... objs) throws SQLException {
		try {
			spawnConnection();
			this.conn.setAutoCommit(false);
			spawnPreparedStatement(sql);
			bindParams(objs);
			this.ps.execute();
			this.conn.commit();
			close();
		} catch (SQLException e) {
			throw new SQLException(
					StringUtil.union("error row : 0, error sql : ", sql, " parameter : ") + StringUtil.union(objs), e);
		}
	}

	public void updateIgnoreError(String sql, Object... objs) {
		try {
			spawnConnection();
			this.conn.setAutoCommit(false);
			spawnPreparedStatement(sql);
			bindParams(objs);
			this.ps.execute();
			this.conn.commit();
			close();
		} catch (SQLException e) {
			System.out.println("error sql : " + sql);
			e.printStackTrace();
		}
	}

	public synchronized void synUpdate(String sql) throws SQLException {
		try {
			spawnConnection();
			this.conn.setAutoCommit(false);
			spawnPreparedStatement(sql);
			this.ps.execute();
			this.conn.commit();
			close();
		} catch (SQLException e) {
			throw new SQLException("error row : " + 0 + ", error sql : " + sql, e);
		}
	}

	public void close() {
		try {
			if (this.conn != null) {
				this.conn.close();
			}
		} catch (SQLException e) {
			this.conn = null;
		}
		try {
			if (this.ps != null) {
				this.ps.close();
			}
		} catch (SQLException e) {
			this.conn = null;
		}
	}

	public void bindParams(Object... objs) throws SQLException {
		for (int i = 0; i < objs.length; i++) {
			if (objs[i] == null) {
				this.ps.setNull(i, Types.NULL);
			} else if (objs[i] instanceof java.util.Date) {
				// extends
			} else if (objs[i] instanceof byte[]) {
				this.ps.setBytes(i + 1, (byte[]) objs[i]);
			} else {
				this.ps.setObject(i + 1, objs[i]);
			}

		}
	}

	public boolean testConn() {
		try {
			List<HashMap<Object, Object>> list = this.select("select 1 from dual;");
			return list != null && list.size() == 1 && list.get(0).get(0).toString().equals("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
