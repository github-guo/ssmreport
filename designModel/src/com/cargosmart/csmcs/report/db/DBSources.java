package com.cargosmart.csmcs.report.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;

public class DBSources {
	private static Logger logger = Logger.getLogger(DBSources.class);
	private final String CONFIG_FILE="dbcp.properties";
	private static DataSource dataSource;
	
	public DBSources(String configFile) {
		loadConfig(configFile);
	}
	
	
	private void loadConfig(String configFile){
		Properties dbcpPro = new Properties();
		try {
			dbcpPro.load(new FileInputStream(new File(configFile==null?CONFIG_FILE:configFile)));
            dataSource =BasicDataSourceFactory.createDataSource(dbcpPro);	
		}  catch (Exception e) {
			logger.error("can not get the properties file");
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("can not get  connection ...");
			e.printStackTrace();
			return null;
		}
	}
}
