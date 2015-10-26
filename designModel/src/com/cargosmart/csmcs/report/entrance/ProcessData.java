package com.cargosmart.csmcs.report.entrance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cargosmart.csmcs.report.domain.Clientusagedata;

public class ProcessData implements Runnable {
	private List<String> registerUserIDs;
	private DataLoadTools dataLoadTools;
	private boolean requeryPublic;
	
	
	int[] refineSearchCounts = {0, 0, 0, 0, 0};
	int[] scheduleReliabilityCounts = {0, 0, 0, 0, 0};
	int[] showMapCounts = {0, 0, 0, 0, 0};
	int[] showMaprefineSearchCounts = {0, 0, 0, 0, 0};
	int[] showMapscheduleReliabilityCounts = {0, 0, 0, 0, 0};
	int[] showMap3Counts = {0, 0, 0, 0, 0};
	int[] othersCounts = {0, 0, 0, 0, 0};
	
	public ProcessData(List<String> registerUserIDs,DataLoadTools dataLoadTools,boolean requeryPublic){
		this.registerUserIDs= registerUserIDs;
		this.dataLoadTools= dataLoadTools;
		this.requeryPublic=requeryPublic;
	}
	
	public void querySearchByUserID(String userID,DataLoadTools dataLoadTools,boolean requeryPublic ) {
		
	
		System.out.println(userID);
		
		String[] manualSearchCodes = {"trace_routes_search","trace_main_routes_search","trace_main_search"};
		String[] favoriteSearchCodes = {"trace_routes_favorite_item", "trace_main_favorite_item"};
		String[] withoutFurtherCodes = {"trace_routes_firstThingAfterSearch"};
		String[] scheduleReliabilityCodes = {"trace_routes_selectSSRRPortPair","trace_routes_clickSSRRImage"};
		String[] showMapCodes = {"trace_routes_selectDetail"};
		String[] refineSearchCodes = {"trace_routes_selectCalendar","trace_routes_selectDirect","trace_routes_selectCycutoffCalendar","trace_routes_selectArrivalCalendar","trace_routes_selectDepartureCalendar","trace_routes_changeTransitTime"};

		List<Clientusagedata> searchedRecords = dataLoadTools.allSearch(userID);
		Map<Clientusagedata, Map<String, Integer>>  userActionTacingMap = new HashMap<Clientusagedata, Map<String, Integer>>();
//		String currentSearchID = null;
		Map<String, Integer> currentSearchMap = null;
		Calendar actionStopCal = null;
		for (Clientusagedata ud : searchedRecords) {
			if (inArray(manualSearchCodes, ud.getFunc()) ||  inArray(favoriteSearchCodes, ud.getFunc())){
//				currentSearchID = ud.getId();
				currentSearchMap = new HashMap<String, Integer>();
				userActionTacingMap.put(ud, currentSearchMap);
				
				actionStopCal = Calendar.getInstance();
				actionStopCal.setTime(ud.getCreateTime());
				actionStopCal.add(Calendar.MINUTE, 30);
				
			} else {
				if (currentSearchMap != null) {
					
					Calendar cal2= Calendar.getInstance();
					cal2.setTime(ud.getCreateTime());
				    
					if (cal2.before(actionStopCal)) {
						String action = ud.getFunc();
						int c = currentSearchMap.containsKey(action) ? currentSearchMap.get(action) + 1 : 1;
						currentSearchMap.put(action, c);
					}
				}
			}
		}
		
		
		
		for (Map.Entry<Clientusagedata, Map<String, Integer>> entry : userActionTacingMap.entrySet()) {
			Clientusagedata ud = entry.getKey();
			Map<String, Integer> followingActionMap = entry.getValue();
			
			
			int idx1 = inArray(manualSearchCodes, ud.getFunc()) ? 2: 3;
			int idx2 = isByMobile(ud) ? 1 : 0;
			
			boolean hasScheduleReliability =false;
			boolean hasRefineSearch = false;
			boolean hasShowMap = false;
			
			for (String action: followingActionMap.keySet()) {
				if (inArray(showMapCodes, action)) {
					hasShowMap = true;
				} else if (inArray(refineSearchCodes, action)||action.startsWith("trace_routes_filter")) {
					hasRefineSearch = true;
				} else if (inArray(scheduleReliabilityCodes, action)) {
					hasScheduleReliability = true;
				}
			}
			
			int i =(hasRefineSearch? 1: 0) + (hasScheduleReliability? 2: 0) +  (hasShowMap? 4: 0 ) ;
			if (i == 1) {
				refineSearchCounts[idx1] += 1;
				refineSearchCounts[idx2] += 1;
				refineSearchCounts[4] += 1;
			} else if (i == 2) {
				scheduleReliabilityCounts[idx1] += 1;
				scheduleReliabilityCounts[idx2] += 1;
				scheduleReliabilityCounts[4] += 1;
			} else if (i == 4) {
				showMapCounts[idx1] += 1;
				showMapCounts[idx2] += 1;
				showMapCounts[4] += 1;
			} else if (i == 5) {
				showMaprefineSearchCounts[idx1] += 1;
				showMaprefineSearchCounts[idx2] += 1;
				showMaprefineSearchCounts[4] += 1;
			} else if (i == 6) {
				showMapscheduleReliabilityCounts[idx1] += 1;
				showMapscheduleReliabilityCounts[idx2] += 1;
				showMapscheduleReliabilityCounts[4] += 1;
			} else if (i == 7) {
				showMap3Counts[idx1] += 1;
				showMap3Counts[idx2] += 1;
				showMap3Counts[4] += 1;
			} else {
				othersCounts[idx1] += 1;
				othersCounts[idx2] += 1;
				othersCounts[4] += 1;
			}
					
		}	
	}
	
	public void lanuch(List<String> registerUserIDs,DataLoadTools dataLoadTools,boolean requeryPublic){
		for (String uid:  registerUserIDs) {
			querySearchByUserID(uid,dataLoadTools,requeryPublic);
		}
		try {
			FileWriter writer = new FileWriter(new File("output-" + (requeryPublic ? "public" : "reg")+ Thread.currentThread().getName() + ".txt"));
			
			writer.write(combineValues(refineSearchCounts) + "\n");
			writer.write(combineValues(scheduleReliabilityCounts) + "\n");
			writer.write(combineValues(showMapCounts) + "\n");
			writer.write(combineValues(showMaprefineSearchCounts) + "\n");
			writer.write(combineValues(showMapscheduleReliabilityCounts) + "\n");
			writer.write(combineValues(showMap3Counts) + "\n");
			writer.write(combineValues(othersCounts) + "\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean inArray(String[] arr, String t) {
		for (String s : arr) {
			if (s.equals(t)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isByMobile(Clientusagedata ud) {
		if(ud==null||ud.getRequestInformation_requestHeaders_user_agent()==null||"".equals(ud.getRequestInformation_requestHeaders_user_agent())){
			return false;
		}
		return ud.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient");
	}
		
	public String combineValues(int[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	@Override
	public void run() {
		long begin = System.currentTimeMillis();
		lanuch(registerUserIDs, dataLoadTools, requeryPublic);
		long end = System.currentTimeMillis();
		System.out.println("spent time " + (end - begin));
	}
}
