package com.cargosmart.csmcs.report.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {
	private final static String DRIVE_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final static String CONNECT_URL = "jdbc:sqlserver://testsql2k5s07:1433;databaseName=CSMCS";
	private final static String USERNAME = "CSMCSadmin";
	private final static String PASSWORD = "csmcsadmin";

	static {
		try {
			Class.forName(DRIVE_CLASS);
		} catch (ClassNotFoundException e) {
			System.err.println("can not connect to db because of load drive class");
		}
	}

	private static Connection connection;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(CONNECT_URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				System.err.println("get connection fail");
			}
		}
		return connection;
	}

	public static ResultSet excuteQuery(String sql) {
		Connection cn = getConnection();
		ResultSet rs = null;
		try {
			rs = cn.prepareStatement(sql).executeQuery();
		} catch (SQLException e) {
			System.err.println("excute query fail");
		}
		return rs;
	}
}
