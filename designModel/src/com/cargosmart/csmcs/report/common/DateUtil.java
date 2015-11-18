package com.cargosmart.csmcs.report.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.cargosmart.csmcs.report.db.Configure;

public class DateUtil {
	static Configure configure = new Configure("config.properties");
	static Integer from=Integer.parseInt(configure.getValue("FROM"));
	static Integer to=Integer.parseInt(configure.getValue("TO"));
	static String startTime=configure.getValue("startTime");
	static String endTime=configure.getValue("endTime");
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	static Calendar cal=Calendar.getInstance();
	static String loadAll = configure.getValue("loadAll");
	public static String getStartTimeByMonths(){
		cal.set(cal.get(Calendar.YEAR), from-1, 1, 0,0,0);
		cal.set(Calendar.MILLISECOND, 0);
		return sdf.format(cal.getTime());
	}
	
	public  static String getEndTimeByMonths(){
		cal.set(Calendar.MONTH,to);
		cal.set(Calendar.DATE,1);
		return sdf.format(cal.getTime());
	}
	
	
	public  String getStartTime(){
		/*Calendar dailyCal =  Calendar.getInstance();
		dailyCal.add(Calendar.DAY_OF_MONTH, -2);
		dailyCal.set(Calendar.HOUR,16);
		dailyCal.set(Calendar.MINUTE,0);
		dailyCal.set(Calendar.SECOND,0);
		dailyCal.set(Calendar.MILLISECOND,0);
		
		return sdf.format(dailyCal.getTime());*/
		return startTime;
	}
	
	public  String getEndTime(){
		/*Calendar dailyCal =  Calendar.getInstance();
		dailyCal.add(Calendar.DATE, -1);
		dailyCal.set(Calendar.HOUR,16);
		dailyCal.set(Calendar.MINUTE,0);
		dailyCal.set(Calendar.SECOND,0);
		dailyCal.set(Calendar.MILLISECOND,0);
		return sdf.format(dailyCal.getTime());*/
		return endTime;
	}
	
	public static int getStartMonth(){
		return from;
	}
	
	public static int getEndMonth(){
		return to;
	}
	
	
	public static void main(String[] args) {
		System.out.println(new DateUtil().getStartTime()+"--->" + new DateUtil().getEndTime());
	}
	
}
