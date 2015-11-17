package com.cargosmart.csmcs.report.common;

import java.util.TimerTask;
import org.apache.log4j.Logger;
import com.cargosmart.csmcs.report.entrance.Entrance;

public class DailyDataInputTask extends TimerTask {
	
	private static Logger logger = Logger.getLogger(Entrance.class);
	@Override
	public void run() {
		long begin = System.currentTimeMillis();
		Entrance et = new Entrance();
		et.executeLogic(false);
		long end = System.currentTimeMillis();
		logger.info("spent time " + (end - begin));
		begin = System.currentTimeMillis();
		et.executeLogic(true);
		end = System.currentTimeMillis();
		logger.info("spent time " + (end - begin));
		logger.info("waiting for the next execute time");
	}
	
}
