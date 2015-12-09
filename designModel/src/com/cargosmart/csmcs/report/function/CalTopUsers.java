package com.cargosmart.csmcs.report.function;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cargosmart.csmcs.report.entrance.DataLoadTools;

public class CalTopUsers {
	private static Logger logger = Logger.getLogger(DataLoadTools.class);
	int sum=0;
	public void calTop(List<String> registerUserIDs, DataLoadTools dataLoadTools) {
		Map<String, Integer> topUserCount=new HashMap<>();
		for(String uid :registerUserIDs){
			int count = dataLoadTools.getSearchCountByUserID(uid);
			topUserCount.put(uid, count);
			sum+=count;
		}
		dataLoadTools.insertSearchCount(topUserCount);
		int top10=dataLoadTools.getTopSumCount(10);
		int top20=dataLoadTools.getTopSumCount(20);
		int top30=dataLoadTools.getTopSumCount(30);
		int top40=dataLoadTools.getTopSumCount(40);
		int top50=dataLoadTools.getTopSumCount(50);
		save2db(dataLoadTools, top10, 10);
		save2db(dataLoadTools, top20, 20);
		save2db(dataLoadTools, top30, 30);
		save2db(dataLoadTools, top40, 40);
		save2db(dataLoadTools, top50, 50);
	}
	
	private void save2db(DataLoadTools dataLoadTools,int top,int loc){
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
