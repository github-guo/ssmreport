package com.cargosmart.csmcs.report.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.cargosmart.csmcs.report.db.Configure;

public class DateUtil {
	static Configure configure = new Configure("config.properties");
	static Integer from=Integer.parseInt(configure.getValue("FROM"));
	static Integer to=Integer.parseInt(configure.getValue("TO"));
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	static Calendar cal=Calendar.getInstance();
	
	public static String getStartTime(){
		cal.set(cal.get(Calendar.YEAR), from-1, 1, 0,0,0);
		cal.set(Calendar.MILLISECOND, 0);
		return sdf.format(cal.getTime());
	}
	
	public  static String getEndTime(){
		cal.set(Calendar.MONTH,to);
		cal.set(Calendar.DATE,1);
		return sdf.format(cal.getTime());
	}
	
	
	public static int getStartMonth(){
		return from;
	}
	
	public static int getEndMonth(){
		return to;
	}
	
}
