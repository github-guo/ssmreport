package com.cargosmart.csmcs.report.db;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Configure {
	private Properties prop;
	
	public Configure(String propFile) {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(propFile)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getValue(String k) {
		return this.prop.getProperty(k);
	}
}
