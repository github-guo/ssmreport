package com.cargosmart.csmcs.report.entrance;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.cargosmart.csmcs.report.domain.Clientusagedata;

public class Entrance {

	private Logger logger = Logger.getLogger(this.getClass());

	// private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
	// hh:mm:ss");

	private DataLoadTools dataLoadTools = new DataLoadTools();

	int[] refineSearchCounts = {0, 0, 0, 0, 0};
	int[] scheduleReliabilityCounts = {0, 0, 0, 0, 0};
	int[] showMapCounts = {0, 0, 0, 0, 0};
	int[] showMaprefineSearchCounts = {0, 0, 0, 0, 0};
	int[] showMapscheduleReliabilityCounts = {0, 0, 0, 0, 0};
	int[] showMap3Counts = {0, 0, 0, 0, 0};
	int[] othersCounts = {0, 0, 0, 0, 0};
	
	public void first() {
		List<String> registerUserIDs = dataLoadTools.getRegistUserIDs();
		
		//querySearchByUserID(registerUserIDs.get(0));
		
		for (String uid:  registerUserIDs) {
			querySearchByUserID(uid);
		}
		pintArray(refineSearchCounts);
		pintArray(scheduleReliabilityCounts);
		pintArray(showMapCounts);
		pintArray(showMaprefineSearchCounts);
		pintArray(showMapscheduleReliabilityCounts);
		pintArray(showMap3Counts);
		pintArray(othersCounts);
		
		// List<String> publishUsersIDs=dataLoadTools.getPublishUserIDs();
		//byManualSearch(registerUserIDs);
//		byFavoriteSearch(registerUserIDs);
		// dataLoadTools.setSearchByIP(true);
		// byManualSearch(publishUsersIDs);
		
		
		//by manual search
		/*System.out.println("by manual/favorite");
		System.out.println("manual_searchWithoutFurtherAction:" + manual_searchWithoutFurtherAction);
		System.out.println("manual_searchReliability:" + manual_searchReliability);
		System.out.println("manual_showMap:" + manual_showMap);
		System.out.println("manual_showMap_refineSearch:" + manual_showMap_refineSearch);
		System.out.println("manual_showMap_scheduleReliability:" + manual_showMap_scheduleReliability);
		System.out.println("manual_showMap_refineSearch_scheduleReliability:" + manual_showMap_refineSearch_scheduleReliability);
		System.out.println("manual_refineSearch:" + manual_refineSearch);
		System.out.println("manual_others:" + manual_others);
		// web or mobile
		System.out.println("manual_searchReliability_mobile:"+manual_searchReliability_mobile);
		System.out.println("manual_searchReliability_web:"+manual_searchReliability_web);
		System.out.println("manual_showMap_mobile:"+manual_showMap_mobile);
		System.out.println("manual_showMap_web:"+manual_showMap_web);
		System.out.println("manual_showMap_refineSearch_mobile:"+manual_showMap_refineSearch_mobile);
		System.out.println("manual_showMap_refineSearch_web:"+manual_showMap_refineSearch_web);
		System.out.println("manual_showMap_scheduleReliability_mobile:"+manual_showMap_scheduleReliability_mobile);
		System.out.println("manual_showMap_scheduleReliability_web:"+manual_showMap_scheduleReliability_web);
		System.out.println("manual_showMap_refineSearch_scheduleReliability_mobile:"+manual_showMap_refineSearch_scheduleReliability_mobile);
		System.out.println("manual_showMap_refineSearch_scheduleReliability_web:"+manual_showMap_refineSearch_scheduleReliability_web);
		System.out.println("manual_refineSearch_mobile:"+manual_refineSearch_mobile);
		System.out.println("manual_refineSearch_web:"+manual_refineSearch_web);
		System.out.println("manual_searchWithoutFurtherAction_mobile:"+manual_searchWithoutFurtherAction_mobile);
		System.out.println("manual_searchWithoutFurtherAction_web:"+manual_searchWithoutFurtherAction_web);
		System.out.println("manual_others_mobile:"+manual_others_mobile);
		System.out.println("manual_others_web:"+manual_others_web);
		
		// /by favorite search
		System.out.println("favorite_searchWithoutFurtherAction:" + favorite_searchWithoutFurtherAction);
		System.out.println("favorite_searchReliability" + favorite_searchReliability);
		System.out.println("favorite_showMap:" + favorite_showMap);
		System.out.println("favorite_refineSearch:" + favorite_refineSearch);
		System.out.println("favorite_showMap_refineSearch:" + favorite_showMap_refineSearch);
		System.out.println("favorite_showMap_scheduleReliability:" + favorite_showMap_scheduleReliability);
		System.out.println("favorite_showMap_refineSearch_scheduleReliability:"+favorite_showMap_refineSearch_scheduleReliability);
		System.out.println("favorite_others:"+favorite_others);
		// web or mobile
		System.out.println("favorite_searchReliability_mobile:"+favorite_searchReliability_mobile);
		System.out.println("favorite_searchReliability_web:"+favorite_searchReliability_web);
		System.out.println("favorite_showMap_mobile:"+favorite_showMap_mobile);
		System.out.println("favorite_showMap_web:"+favorite_showMap_web);
		System.out.println("favorite_showMap_refineSearch_mobile:"+favorite_showMap_refineSearch_mobile);
		System.out.println("favorite_showMap_refineSearch_web:"+favorite_showMap_refineSearch_web);
		System.out.println("favorite_showMap_scheduleReliability_mobile:"+favorite_showMap_scheduleReliability_mobile);
		System.out.println("favorite_showMap_scheduleReliability_web:"+favorite_showMap_scheduleReliability_web);
		System.out.println("favorite_showMap_refineSearch_scheduleReliability_mobile:"+favorite_showMap_refineSearch_scheduleReliability_mobile);
		System.out.println("favorite_showMap_refineSearch_scheduleReliability_web:"+favorite_showMap_refineSearch_scheduleReliability_web);
		System.out.println("favorite_refineSearch_mobile:"+favorite_refineSearch_mobile);
		System.out.println("favorite_refineSearch_web:"+favorite_refineSearch_web);
		System.out.println("favorite_searchWithoutFurtherAction_mobile:"+favorite_searchWithoutFurtherAction_mobile);
		System.out.println("favorite_searchWithoutFurtherAction_web:"+favorite_searchWithoutFurtherAction_web);
		System.out.println("favorite_others_mobile:"+favorite_others_mobile);
		System.out.println("favorite_others_web:"+favorite_others_web);*/
		
	}

	public static void main(String[] args) {
        long begin = System.currentTimeMillis();
		new Entrance().first();
		long end = System.currentTimeMillis();
		System.out.println("spent time "+(end-begin));
	}

	public boolean inArray(String[] arr, String t) {
		for (String s : arr) {
			if (s.equals(t)) {
				return true;
			}
		}
		return false;
	}
	
	public void querySearchByUserID(String userID) {
		System.out.println(userID);
		
		String[] manualSearchCodes = {"trace_routes_search","trace_main_routes_searchv","trace_main_search"};
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
				} else if (inArray(refineSearchCodes, action)) {
					hasRefineSearch = true;
				} else if (inArray(scheduleReliabilityCodes, action)) {
					hasScheduleReliability = true;
				}
			}
			
			if (hasRefineSearch){
				refineSearchCounts[idx1] += 1;
				refineSearchCounts[idx2] += 1;
				refineSearchCounts[4] += 1;
			}
			if (hasScheduleReliability) {
				scheduleReliabilityCounts[idx1] += 1;
				scheduleReliabilityCounts[idx2] += 1;
				scheduleReliabilityCounts[4] += 1;
			}
			if (hasShowMap) {
				showMapCounts[idx1] += 1;
				showMapCounts[idx2] += 1;
				showMapCounts[4] += 1;
			}
			if (hasShowMap && hasRefineSearch) {
				showMaprefineSearchCounts[idx1] += 1;
				showMaprefineSearchCounts[idx2] += 1;
				showMaprefineSearchCounts[4] += 1;
			}
			if (hasShowMap && hasScheduleReliability) {
				showMapscheduleReliabilityCounts[idx1] += 1;
				showMapscheduleReliabilityCounts[idx2] += 1;
				showMapscheduleReliabilityCounts[4] += 1;
			}
			if (hasShowMap && hasRefineSearch && hasScheduleReliability) {
				showMap3Counts[idx1] += 1;
				showMap3Counts[idx2] += 1;
				showMap3Counts[4] += 1;
			}
			if (!(hasShowMap || hasRefineSearch || hasScheduleReliability)) {
				othersCounts[idx1] += 1;
				othersCounts[idx2] += 1;
				othersCounts[4] += 1;
			}
					
		}
		
		/*pintArray(refineSearchCounts);
		pintArray(scheduleReliabilityCounts);
		pintArray(showMapCounts);
		pintArray(showMaprefineSearchCounts);
		pintArray(showMapscheduleReliabilityCounts);
		pintArray(showMap3Counts);
		pintArray(othersCounts);*/
		
	}
	
	public void pintArray(int[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(arr[i]);
		}
		System.out.println(sb.toString());
	}
	public boolean isByMobile(Clientusagedata ud) {
		return ud.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient");
	}
	
	public void byManualSearch(List<String> registerUserIDs) {
		
		for (String id : registerUserIDs) {
			System.out.println(id);
			// load the steps data by user id
			List<Clientusagedata> manaualSearch = dataLoadTools.manualSearch(id);
			List<Clientusagedata> withoutFurtherAction = dataLoadTools.WithoutFurtherAction(id);
			List<Clientusagedata> RefineSearch = dataLoadTools.RefineSearch(id);
			List<Clientusagedata> scheduleReliability = dataLoadTools.ScheduleReliability(id);
			List<Clientusagedata> ShowMap = dataLoadTools.ShowMap(id);
			// List<Clientusagedata>
			// favoriteSearch=dataLoadTools.favoriteSearch(id);

			logger.debug("manaualSearch size:" + manaualSearch.size());
			logger.debug("WithoutFurtherAction size:" + withoutFurtherAction.size());
			logger.debug("RefineSearch size:" + RefineSearch.size());
			logger.debug("ShowMap size:" + ShowMap.size());
			logger.debug("ScheduleReliability size:" + scheduleReliability.size());

			for (int i = 0; i < manaualSearch.size(); i++) {

				boolean isSearchReliability = false;
				boolean isShowmap = false;
				boolean isRefineSearch = false;
				boolean isWithoutFurtherAction = false;

				Clientusagedata firstSearchRecord = manaualSearch.get(i);
				Clientusagedata nextSearchRecord = null;

				if (i != manaualSearch.size() - 1) {
					nextSearchRecord = manaualSearch.get(i + 1);
				} else {
					nextSearchRecord = firstSearchRecord;
				}
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(firstSearchRecord.getCreateTime());
				calendar.add(Calendar.MINUTE, 30);

				isSearchReliability = commonPart(scheduleReliability, firstSearchRecord, nextSearchRecord, calendar);
				isShowmap = commonPart(ShowMap, firstSearchRecord, nextSearchRecord, calendar);
				isRefineSearch = commonPart(RefineSearch, firstSearchRecord, nextSearchRecord, calendar);
				isWithoutFurtherAction = commonPart(withoutFurtherAction, firstSearchRecord, nextSearchRecord,
						calendar);

				if (isSearchReliability == true && isShowmap == false && isRefineSearch == false) {
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isShowmap == true && isRefineSearch == false && isSearchReliability == false) {
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isShowmap == true && isRefineSearch == true && isSearchReliability == false) {
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isShowmap == true && isSearchReliability == true && isRefineSearch == false) {
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isShowmap == true && isRefineSearch == true && isSearchReliability == true) {
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isRefineSearch == true && isShowmap == false && isSearchReliability == false) {
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isWithoutFurtherAction == true && isRefineSearch == false && isSearchReliability == false
						&& isShowmap == false) {
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else {
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				}

			}
		}
	}

	public boolean commonPart(List<Clientusagedata> records, Clientusagedata firstSearchRecord,
			Clientusagedata nextSearchRecord, Calendar calendar) {
		logger.debug("first search time:" + firstSearchRecord.getCreateTime() + ",second search time:"
				+ nextSearchRecord.getCreateTime());
		boolean isPair = false;
		if (records != null && records.size() > 0) {
			for (int i = 0; i < records.size(); i++) {
				Clientusagedata record = records.get(i);
				// searchtime < scheduleReliability < searchtime+30min

				if (firstSearchRecord.getCreateTime().before(record.getCreateTime())
						&& calendar.getTime().after(record.getCreateTime())) {
					// scheduleReliability < nextSearchRecor
					if (firstSearchRecord.equals(nextSearchRecord)
							|| record.getCreateTime().before(nextSearchRecord.getCreateTime())) {
						isPair = true;
						records.remove(record);
					} else {
						isPair = false;
					}
				}
			}
		}
		return isPair;
	}

	public void byFavoriteSearch(List<String> users) {

		for (String id : users) {
			System.out.println(id);
			// load the steps data by user id
			List<Clientusagedata> favoriteSearch = dataLoadTools.favoriteSearch(id);
			List<Clientusagedata> withoutFurtherAction = dataLoadTools.WithoutFurtherAction(id);
			List<Clientusagedata> RefineSearch = dataLoadTools.RefineSearch(id);
			List<Clientusagedata> scheduleReliability = dataLoadTools.ScheduleReliability(id);
			List<Clientusagedata> ShowMap = dataLoadTools.ShowMap(id);
			// List<Clientusagedata>
			// favoriteSearch=dataLoadTools.favoriteSearch(id);

			logger.debug("favorite size:" + favoriteSearch.size());
			logger.debug("WithoutFurtherAction size:" + withoutFurtherAction.size());
			logger.debug("RefineSearch size:" + RefineSearch.size());
			logger.debug("ShowMap size:" + ShowMap.size());
			logger.debug("ScheduleReliability size:" + scheduleReliability.size());

			for (int i = 0; i < favoriteSearch.size(); i++) {

				boolean isSearchReliability = false;
				boolean isShowmap = false;
				boolean isRefineSearch = false;
				boolean isWithoutFurtherAction = false;

				Clientusagedata firstSearchRecord = favoriteSearch.get(i);
				Clientusagedata nextSearchRecord = null;

				if (i != favoriteSearch.size() - 1) {
					nextSearchRecord = favoriteSearch.get(i + 1);
				} else {
					nextSearchRecord = firstSearchRecord;
				}
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(firstSearchRecord.getCreateTime());
				calendar.add(Calendar.MINUTE, 30);

				isSearchReliability = commonPart(scheduleReliability, firstSearchRecord, nextSearchRecord, calendar);
				isShowmap = commonPart(ShowMap, firstSearchRecord, nextSearchRecord, calendar);
				isRefineSearch = commonPart(RefineSearch, firstSearchRecord, nextSearchRecord, calendar);
				isWithoutFurtherAction = commonPart(withoutFurtherAction, firstSearchRecord, nextSearchRecord,
						calendar);

				if (isSearchReliability == true && isShowmap == false && isRefineSearch == false) {
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isShowmap == true && isRefineSearch == false && isSearchReliability == false) {
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isShowmap == true && isRefineSearch == true && isSearchReliability == false) {
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isShowmap == true && isSearchReliability == true && isRefineSearch == false) {
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isShowmap == true && isRefineSearch == true && isSearchReliability == true) {
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isRefineSearch == true && isShowmap == false && isSearchReliability == false) {
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else if (isWithoutFurtherAction == true && isRefineSearch == false && isSearchReliability == false
						&& isShowmap == false) {
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				} else {
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
					}else{
					}
				}

			}
		}
	}

}
