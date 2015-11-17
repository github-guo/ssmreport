package com.cargosmart.csmcs.report.common;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.apache.log4j.Logger;

import com.cargosmart.csmcs.report.db.Configure;

public class TimerManager {
	static Logger logger =Logger.getLogger(TimerManager.class);
	static Configure configure = new Configure("timerTask.properties");
	static long interval;
	static int hour;
	static int min;
	static{
		interval = Integer.parseInt(configure.getValue("interval"));//24 * 60 * 60 * 1000;
		hour=Integer.parseInt(configure.getValue("start-hour"));
		min=Integer.parseInt(configure.getValue("start-min"));
	}

	public TimerManager() {
		
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR_OF_DAY,hour);
		calendar.set(Calendar.MINUTE,min);
		calendar.set(Calendar.SECOND, 0);
		logger.info("program will execute at "+hour+":"+min +" and interval is "+interval);
		Date date = calendar.getTime(); // 第一次执行定时任务的时间
		
		// 如果第一次执行定时任务的时间 小于 当前的时间
		// 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}

		Timer timer = new Timer();

		DailyDataInputTask task = new DailyDataInputTask();
		// 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		timer.schedule(task, date, interval);
	}

	// 增加或减少天数
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
}
