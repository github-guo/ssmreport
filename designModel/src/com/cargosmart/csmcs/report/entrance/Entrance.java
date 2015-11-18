package com.cargosmart.csmcs.report.entrance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cargosmart.csmcs.report.common.DateUtil;
import com.cargosmart.csmcs.report.common.JourneyType;
import com.cargosmart.csmcs.report.common.TimerManager;
import com.cargosmart.csmcs.report.db.DBSources;
import com.cargosmart.csmcs.report.domain.Clientusagedata;
import com.cargosmart.csmcs.report.domain.SearchDetailObject;

public class Entrance {
	private static Logger logger = Logger.getLogger(Entrance.class);

	private DataLoadTools dataLoadTools = new DataLoadTools();
	private Map<JourneyType, int[]> resultDataMap = new HashMap<>();

	int[] refineSearchCounts = { 0, 0, 0, 0, 0 };
	int[] scheduleReliabilityCounts = { 0, 0, 0, 0, 0 };
	int[] showMapCounts = { 0, 0, 0, 0, 0 };
	int[] showMaprefineSearchCounts = { 0, 0, 0, 0, 0 };
	int[] showMapscheduleReliabilityCounts = { 0, 0, 0, 0, 0 };
	int[] showMap3Counts = { 0, 0, 0, 0, 0 };
	int[] othersCounts = { 0, 0, 0, 0, 0 };
	int[] searchEndCounts = { 0, 0, 0, 0, 0 };
	int[] searchRegisterCounts = { 0, 0, 0, 0, 0 };

	String[] manualSearchCodes = { "trace_routes_search", "trace_main_routes_search", "trace_main_search",
			"trace_routes_search_public" };
	String[] favoriteSearchCodes = { "trace_routes_favorite_item", "trace_main_favorite_item" };
	String[] scheduleReliabilityCodes = { "trace_routes_selectSSRRPortPair", "trace_routes_clickSSRRImage" };
	String[] showMapCodes = { "trace_routes_selectDetail" };
	String[] refineSearchCodes = { "trace_routes_selectCalendar", "trace_routes_selectDirect",
			"trace_routes_selectCycutoffCalendar", "trace_routes_selectArrivalCalendar",
			"trace_routes_selectDepartureCalendar", "trace_routes_changeTransitTime" };
	String[] searchRegister = { "trace_common_click_signup" };
	String[] searchEnd = { "trace_routes_focusRoutes", "trace_routes_firstThingAfterSearch" };

	public void outputToFile(String filename) {
		try {
			FileWriter writer = new FileWriter(new File(filename));
			writer.write(combineValues(searchEndCounts) + "\n");
			writer.write(combineValues(refineSearchCounts) + "\n");
			writer.write(combineValues(scheduleReliabilityCounts) + "\n");
			writer.write(combineValues(showMapCounts) + "\n");
			writer.write(combineValues(showMaprefineSearchCounts) + "\n");
			writer.write(combineValues(showMapscheduleReliabilityCounts) + "\n");
			writer.write(combineValues(showMap3Counts) + "\n");
			writer.write(combineValues(othersCounts) + "\n");
			writer.write(combineValues(searchRegisterCounts) + "\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void executeLogic(boolean publicUser) {

		refineSearchCounts = new int[] { 0, 0, 0, 0, 0 };
		scheduleReliabilityCounts = new int[] { 0, 0, 0, 0, 0 };
		showMapCounts = new int[] { 0, 0, 0, 0, 0 };
		showMaprefineSearchCounts = new int[] { 0, 0, 0, 0, 0 };
		showMapscheduleReliabilityCounts = new int[] { 0, 0, 0, 0, 0 };
		showMap3Counts = new int[] { 0, 0, 0, 0, 0 };
		othersCounts = new int[] { 0, 0, 0, 0, 0 };
		searchEndCounts = new int[] { 0, 0, 0, 0, 0 };
		searchRegisterCounts = new int[] { 0, 0, 0, 0, 0 };

		dataLoadTools.setSearchByIP(publicUser);

		List<String> registerUserIDs = null;
		if (publicUser) {
			registerUserIDs = dataLoadTools.getPublishUserIPs();
		} else {
			registerUserIDs = dataLoadTools.getRegistUserIDs();
		}

		logger.info(publicUser == true ? "publish users number:" + registerUserIDs.size()
				: "register users numbers" + registerUserIDs.size());

		
		for (String uid : registerUserIDs) {
			querySearchByUserID(uid, publicUser);
		}
		 
//		outputToFile("output-" + (publicUser ? "public" : "reg") + ".txt");
	}

	private void outputToDB(boolean requeryPublic, List<String> registerUserIDs) {
		resultDataMap.put(JourneyType.REFINE_SEARCH, refineSearchCounts);
		resultDataMap.put(JourneyType.SCHEDULE_RELIABILITY, scheduleReliabilityCounts);
		resultDataMap.put(JourneyType.SHOW_MAP, showMapCounts);
		resultDataMap.put(JourneyType.SHOW1, showMaprefineSearchCounts);
		resultDataMap.put(JourneyType.SHOW2, showMapscheduleReliabilityCounts);
		resultDataMap.put(JourneyType.SHOW3, showMap3Counts);
		resultDataMap.put(JourneyType.OTHERS, othersCounts);
		DBSources db = new DBSources(null);
		Connection con = db.getConnection();
		String sql = "INSERT INTO `journeyreport` (`journeyType`, `web`, `mobile`, `manual`, `favorite`, `total`, `usertype`, `from_month`,`to_month`) VALUES (?,?,?,?,?,?,?,?,?)";
		for (JourneyType type : resultDataMap.keySet()) {
			int[] searchRs = resultDataMap.get(type);
			try {
				PreparedStatement pre = con.prepareStatement(sql);
				pre.setString(1, type.toString());
				pre.setInt(2, searchRs[0]);
				pre.setInt(3, searchRs[1]);
				pre.setInt(4, searchRs[2]);
				pre.setInt(5, searchRs[3]);
				pre.setInt(6, searchRs[4]);
				pre.setString(7, (requeryPublic ? "public" : "register"));
				pre.setInt(8, DateUtil.getStartMonth());
				pre.setInt(9, DateUtil.getEndMonth());
				pre.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		logger.info("waiting for the right time to execute ");
		new TimerManager();
	}

	public boolean inArray(String[] arr, String t) {
		for (String s : arr) {
			if (s.equals(t)) {
				return true;
			}
		}
		return false;
	}

	public void querySearchByUserID(String userID, boolean publicUser) {
		logger.info("processing user id or ip is " + userID);

		List<Clientusagedata> searchedRecords = dataLoadTools.allSearch(userID);
		List<SearchDetailObject> searchDetailList = new ArrayList<>();
		String searchSegment = dataLoadTools.getSearchSegment(userID);
		Map<Clientusagedata, Map<String, Integer>> userActionTacingMap = new HashMap<Clientusagedata, Map<String, Integer>>();
		// String currentSearchID = null;
		Map<String, Integer> currentSearchMap = null;
		Calendar actionStopCal = null;
		if (searchedRecords == null || searchedRecords.size() == 0) {
			return;
		}

		// get key -value (the collection of search records before next search.)
		for (Clientusagedata ud : searchedRecords) {
			if (inArray(manualSearchCodes, ud.getFunc()) || inArray(favoriteSearchCodes, ud.getFunc())) {
				// currentSearchID = ud.getId();
				currentSearchMap = new HashMap<String, Integer>();
				userActionTacingMap.put(ud, currentSearchMap);

				actionStopCal = Calendar.getInstance();
				actionStopCal.setTime(ud.getCreateTime());
				actionStopCal.add(Calendar.MINUTE, 30);

			} else {
				if (currentSearchMap != null) {

					Calendar cal2 = Calendar.getInstance();
					cal2.setTime(ud.getCreateTime());

					if (cal2.before(actionStopCal)) {
						String action = ud.getFunc();
						int c = currentSearchMap.containsKey(action) ? currentSearchMap.get(action) + 1 : 1;
						currentSearchMap.put(action, c);
					}
				}
			}
		}

		// for each search loop
		for (Map.Entry<Clientusagedata, Map<String, Integer>> entry : userActionTacingMap.entrySet()) {
			SearchDetailObject detailObject = new SearchDetailObject();
			Clientusagedata ud = entry.getKey();
			detailObject.setId(ud.getId());
			Map<String, Integer> followingActionMap = entry.getValue();
			boolean isManualSearch = inArray(manualSearchCodes, ud.getFunc());
			boolean isMobile = isByMobile(ud);
			detailObject.setCustomerType(publicUser ? "Public Users" : "Registered Users");
			detailObject.setIp(ud.getRequestInformation_requestIp());
			detailObject.setSearchDate(ud.getCreateTime());
			detailObject.setSearchMethod(isManualSearch ? "manual" : "favorite");
			detailObject.setSearchPlatform(isMobile ? "mobile" : "web");
			detailObject.setUserId(ud.getUserIdentification_userID());
			detailObject.setCustomerSegment(searchSegment);
			int idx1 = isManualSearch ? 2 : 3;
			int idx2 = isMobile ? 1 : 0;

			boolean hasScheduleReliability = false;
			boolean hasRefineSearch = false;
			boolean hasShowMap = false;
			boolean hasRegister = false;
			boolean hasSearchEnd = false;
			JourneyType searchType;
			int otherFunCount = 0;
			// for each action record loop
			for (String action : followingActionMap.keySet()) {
				if (inArray(showMapCodes, action)) {
					hasShowMap = true;
				} else if (inArray(refineSearchCodes, action) || action.startsWith("trace_routes_filter")) {
					hasRefineSearch = true;
				} else if (inArray(scheduleReliabilityCodes, action)) {
					hasScheduleReliability = true;
				} else if (inArray(searchRegister, action)) {
					hasRegister = true;
					logger.info("".equals(ud.getUserIdentification_userID())
							? "register ip:" + ud.getRequestInformation_requestIp() + " register time:"
									+ ud.getCreateTime()
							: "register user id:" + ud.getUserIdentification_userID() + " register time:"
									+ ud.getCreateTime());
				} else if (inArray(searchEnd, action)) {
					hasSearchEnd = true;
				} else {
					otherFunCount++;
				}
			}

			int i = (hasRefineSearch ? 1 : 0) + (hasScheduleReliability ? 2 : 0) + (hasShowMap ? 4 : 0);
			// + (hasSearchEnd ? 8 : 0) + (hasRegister ? 16 : 0);
			if (i == 1) {
				refineSearchCounts[idx1] += 1;
				refineSearchCounts[idx2] += 1;
				refineSearchCounts[4] += 1;
				searchType = JourneyType.REFINE_SEARCH;
			} else if (i == 2) {
				scheduleReliabilityCounts[idx1] += 1;
				scheduleReliabilityCounts[idx2] += 1;
				scheduleReliabilityCounts[4] += 1;
				searchType = JourneyType.SCHEDULE_RELIABILITY;
			} else if (i == 4) {
				showMapCounts[idx1] += 1;
				showMapCounts[idx2] += 1;
				showMapCounts[4] += 1;
				searchType = JourneyType.SHOW_MAP;
			} else if (i == 5) {
				showMaprefineSearchCounts[idx1] += 1;
				showMaprefineSearchCounts[idx2] += 1;
				showMaprefineSearchCounts[4] += 1;
				searchType = JourneyType.SHOW1;
			} else if (i == 6) {
				showMapscheduleReliabilityCounts[idx1] += 1;
				showMapscheduleReliabilityCounts[idx2] += 1;
				showMapscheduleReliabilityCounts[4] += 1;
				searchType = JourneyType.SHOW2;
			} else if (i == 7) {
				showMap3Counts[idx1] += 1;
				showMap3Counts[idx2] += 1;
				showMap3Counts[4] += 1;
				searchType = JourneyType.SHOW3;
			} else if (hasRegister) {
				searchRegisterCounts[idx1] += 1;
				searchRegisterCounts[idx2] += 1;
				searchRegisterCounts[4] += 1;
				searchType = JourneyType.SEARCH_REGISTER;
			} else if (hasSearchEnd && otherFunCount == 0 && i == 0) {
				logger.debug("".equals(ud.getUserIdentification_userID())
						? "ip:" + ud.getRequestInformation_requestIp() + " search type is withoutFurther,time:"
								+ ud.getCreateTime()
						: "user id:" + ud.getUserIdentification_userID() + " search type is withoutFurther,time:"
								+ ud.getCreateTime());
				searchEndCounts[idx1] += 1;
				searchEndCounts[idx2] += 1;
				searchEndCounts[4] += 1;
				searchType = JourneyType.SEARCH_END;
			} else {
				othersCounts[idx1] += 1;
				othersCounts[idx2] += 1;
				othersCounts[4] += 1;
				searchType = JourneyType.OTHERS;
			}
			detailObject.setSearchJourney(searchType.toString());
			searchDetailList.add(detailObject);
		}

		dataLoadTools.writeSearchDetails(searchDetailList);

	}

	public void pintArray(int[] arr) {

		System.out.println(combineValues(arr));
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

	public boolean isByMobile(Clientusagedata ud) {
		if (ud == null || ud.getRequestInformation_requestHeaders_user_agent() == null
				|| "".equals(ud.getRequestInformation_requestHeaders_user_agent())) {
			return false;
		}
		return ud.getRequestInformation_requestHeaders_user_agent()
				.matches("(^Apache-HttpClient.*)|(^Big Schedules(.*)iPhone$)");
	}

}
