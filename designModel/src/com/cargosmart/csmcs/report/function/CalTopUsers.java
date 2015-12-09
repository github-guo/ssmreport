package com.cargosmart.csmcs.report.function;

import java.text.NumberFormat;

import org.apache.log4j.Logger;

import com.cargosmart.csmcs.report.entrance.DataLoadTools;

public class CalTopUsers {
	private static Logger logger = Logger.getLogger(DataLoadTools.class);
	private DataLoadTools dataLoadTools = new DataLoadTools();
	int sum=0;
	public void calTop() {
		
		dataLoadTools.setSearchByIP(false);
		sum = dataLoadTools.copyUsageAnalysis2TopUsage();
		int top10=dataLoadTools.getTopSumCount(10);
		int top20=dataLoadTools.getTopSumCount(20);
		int top30=dataLoadTools.getTopSumCount(30);
		int top40=dataLoadTools.getTopSumCount(40);
		int top50=dataLoadTools.getTopSumCount(50);
		save2db(top10, 10);
		save2db(top20, 20);
		save2db(top30, 30);
		save2db(top40, 40);
		save2db(top50, 50);
	}
	
	private void save2db(int top,int loc){
		float tmp_top=new Float(top);
		float tmp_sum=new Float(sum);
		NumberFormat format = NumberFormat.getInstance();
	    format.setMaximumFractionDigits(2);
	    StringBuffer sb = new StringBuffer();
	    sb.append("TOP ");
	    sb.append(loc);
	    float per =new Float(format.format(tmp_top/tmp_sum));
	    dataLoadTools.save2usageRange(sb.toString(),top,per);
		logger.info("top "+loc+":"+per);
	}
}
