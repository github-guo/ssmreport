package com.cargosmart.csmcs.report.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBHelper {
	private String DRIVE_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String CONNECT_URL = "jdbc:sqlserver://testsql2k5s07:1433;databaseName=CSMCS";
	private String USERNAME = "CSMCSadmin";
	private String PASSWORD = "csmcsadmin";
	private Logger logger = Logger.getLogger(DBHelper.class);

	private Connection connection;

	public void loadConfigure(Configure config) {
		this.DRIVE_CLASS = config.getValue("DRIVE_CLASS");
		this.CONNECT_URL = config.getValue("CONNECT_URL");
		this.USERNAME = config.getValue("USERNAME");
		this.PASSWORD = config.getValue("PASSWORD");

		try {
			Class.forName(DRIVE_CLASS);
		} catch (ClassNotFoundException e) {
			System.err.println("can not connect to db because of load drive class");
		}
	}

	public Connection getConnection() {

		if (connection == null) {
			try {
				connection = DriverManager.getConnection(CONNECT_URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("get connection fail");
			}
		}
		return connection;
	}

	public ResultSet excuteQuery(String sql) {
		long begin = System.currentTimeMillis();
		Connection cn = getConnection();
		ResultSet rs = null;
		try {
			rs = cn.prepareStatement(sql).executeQuery();
		} catch (SQLException e) {
			System.err.println("excute query fail");
		}
		long end = System.currentTimeMillis();
		if ((end - begin) > 5000) {
			logger.info("sql :" + sql + "\n execute time:" + (end - begin));
		}
		return rs;
	}

}
