package com.cargosmart.csmcs.report.entrance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cargosmart.csmcs.report.domain.Clientusagedata;

public class Entrance {

	private DataLoadTools dataLoadTools = new DataLoadTools();

	public void first(boolean requeryPublic) {

			
		dataLoadTools.setSearchByIP(requeryPublic);
		
		List<String> registerUserIDs = null;
		if (requeryPublic) {
			registerUserIDs = dataLoadTools.getPublishUserIDs();
		} else {
			registerUserIDs = dataLoadTools.getRegistUserIDs();
		}
		int count=registerUserIDs.size()/2;
		List<String> part1=new ArrayList<String>();
		List<String> part2=new ArrayList<String>();
		
		for(int i=0;i<count;i++){
			part1.add(registerUserIDs.get(i));
		}
		for(int i=count;i<registerUserIDs.size();i++){
			part2.add(registerUserIDs.get(i));
		}
		ProcessData p1=new ProcessData(part1, dataLoadTools, requeryPublic);
		ProcessData p2=new ProcessData(part2, dataLoadTools, requeryPublic);
		Thread t1=new Thread(p1);
		Thread t2=new Thread(p2);
		t1.start();
		t2.start();
//		pintArray(refineSearchCounts);
//		pintArray(scheduleReliabilityCounts);
//		pintArray(showMapCounts);
//		pintArray(showMaprefineSearchCounts);
//		pintArray(showMapscheduleReliabilityCounts);
//		pintArray(showMap3Counts);
//		pintArray(othersCounts);
	}

	public static void main(String[] args) {
		
		Entrance et = new Entrance();
		Entrance et2=new Entrance();
		et.first(false);
		et2.first(true);
	}
}
