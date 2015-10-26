package com.cargosmart.csmcs.report.entrance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cargosmart.csmcs.report.common.Configure;
import com.cargosmart.csmcs.report.db.DBHelper;
import com.cargosmart.csmcs.report.domain.Clientusagedata;

public class DataLoadTools {
	private static final String SQL_FIND_PUBLISH_USERID = "select DISTINCT(cd.requestInformation#requestIp) from CSSOWNER.CLIENTUSAGEDATAS as cd where cd.requestInformation#requestIp IS NOT NULL and LEN(cd.userIdentification#userID)=0 and not exists (select 1 from POI.BS_IP_DATA_XLSX_LIST i where cd.requestInformation#requestIp=i.IP_ADDRESS) order by cd.requestInformation#requestIp";
	private static final String SQL_FIND_REGIST_USERID = " select distinct(cd.userIdentification#userID)from CSSOWNER.CLIENTUSAGEDATAS as cd where cd.userIdentification#userID IS NOT NULL and cd.userIdentification#userID!='' and not exists (select 1 from POI.BS_IP_DATA_XLSX_LIST i where cd.requestInformation#requestIp=i.IP_ADDRESS)and not exists (select 1 from POI.BS_INTERNAL_USE_LIST u where upper(cd.userIdentification#userID)=upper(u.[OFFICE EMAIL ADDRESS]))  ORDER BY cd.userIdentification#userID";
	private String sqlConditon = "cd.userIdentification#userID";
	private final String EXCLUDE_INTERNAL_USER = "and not exists (select 1 from POI.BS_IP_DATA_XLSX_LIST i where cd.requestInformation#requestIp=i.IP_ADDRESS) and not exists (select 1 from POI.BS_INTERNAL_USE_LIST u where upper(cd.userIdentification#userID)=upper(u.[OFFICE EMAIL ADDRESS])) ORDER BY cd.createTime";

	private DBHelper dbHelper;
	private static Logger logger = Logger.getLogger(DataLoadTools.class);

	public DataLoadTools() {
		dbHelper = new DBHelper();
		dbHelper.loadConfigure(new Configure("config.properties"));
	}

	private List<String> getUserWithCondition(String sql) {

		ResultSet rs = dbHelper.excuteQuery(sql);

		List<String> registerIDs = new ArrayList<>();
		try {
			while (rs.next() != false) {
				String id = rs.getString(1);
				registerIDs.add(id);
				// System.out.println(id);
			}
		} catch (SQLException e) {
			System.err.println("can not get result from db ");
			e.printStackTrace();
		}
		return registerIDs;
	}

	public List<String> getRegistUserIDs() {
		logger.info("getRegistUserIDs start...pls wait");
		return getUserWithCondition(SQL_FIND_REGIST_USERID);
	}

	public List<String> getPublishUserIDs() {
		logger.info("getPublishUserIDs start");
		return getUserWithCondition(SQL_FIND_PUBLISH_USERID);
	}

	private List<Clientusagedata> resultStepByStep(String sql) {
		List<Clientusagedata> searchRs = new ArrayList<>();
		ResultSet rs = dbHelper.excuteQuery(sql);
		try {
			Clientusagedata obj = null;
			while (rs.next() != false) {
				obj = new Clientusagedata();
				obj.setId(rs.getString(1));
				obj.setSource(rs.getString(2));
				obj.setFunc(rs.getString(4));
				obj.setCreateTime(rs.getTimestamp(5));
				obj.setRequestInformation_requestIp(rs.getString(6));
				obj.setUserIdentification_userID(rs.getString(10));
				obj.setRequestInformation_requestHeaders_user_agent(rs.getString(16));
				searchRs.add(obj);
			}
		} catch (SQLException e) {
			System.err.println("can not get result from db");
		}
		return searchRs;
	}

	public List<Clientusagedata> allSearch(String id) {
		String sql = "SELECT *FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE " + sqlConditon + " ='" + id + "' "
				+ (sqlConditon == "cd.requestInformation#requestIp" ? " and LEN(cd.userIdentification#userID)=0 " : "")
				+ EXCLUDE_INTERNAL_USER;
		List<Clientusagedata> searchRecords = resultStepByStep(sql);
		return searchRecords;

	}

	public void setSearchByIP(boolean byIP) {
		if (byIP == true) {
			sqlConditon = "cd.requestInformation#requestIp";
		}
	}

	// public List<Clientusagedata> manualSearch(String id) {
	//
	// System.out.println("manualSearch start");
	// // SELECT * FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE  condition=' '
	// //  and cd.func in
	// //
	// ('trace_routes_search','trace_main_routes_search','trace_main_search');
	// String sql = "SELECT *FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE " +
	// sqlConditon + " ='" + id
	// + "' and cd.func in
	// ('trace_routes_search','trace_main_routes_search','trace_main_search') "
	// + EXCLUDE_INTERNAL_USER;
	// List<Clientusagedata> searchRecords=resultStepByStep(sql);
	// return searchRecords;
	// }
	//
	// public List<Clientusagedata> favoriteSearch(String id) {
	// System.out.println("favoriteSearch start");
	// String sql = "SELECT *FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE " +
	// sqlConditon + "='" + id
	// + "' and cd.func in ('trace_routes_favorite_item',
	// 'trace_main_favorite_item')" + EXCLUDE_INTERNAL_USER;
	// return resultStepByStep(sql);
	// }
	//
	// public List<Clientusagedata> WithoutFurtherAction(String id) {
	// System.out.println("WithoutFurtherAction start");
	// String sql = "SELECT *FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE " +
	// sqlConditon + " ='" + id
	// + "' and cd.func='trace_routes_firstThingAfterSearch' " +
	// EXCLUDE_INTERNAL_USER;
	// return resultStepByStep(sql);
	// }
	//
	// public List<Clientusagedata> ScheduleReliability(String id) {
	// System.out.println("scheduleReliability start ");
	// String sql = "SELECT *FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE " +
	// sqlConditon + " ='" + id
	// + "' and cd.func in
	// ('trace_routes_selectSSRRPortPair','trace_routes_clickSSRRImage') "
	// + EXCLUDE_INTERNAL_USER;
	// return resultStepByStep(sql);
	// }
	//
	// public List<Clientusagedata> ShowMap(String id) {
	// System.out.println("ShowMap start");
	// String sql = "SELECT *FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE " +
	// sqlConditon + " ='" + id
	// + "' and cd.func='trace_routes_selectDetail' " + EXCLUDE_INTERNAL_USER;
	// return resultStepByStep(sql);
	// }
	//
	// public List<Clientusagedata> RefineSearch(String id) {
	// System.out.println("RefineSearch start");
	// /**
	// * SELECT * FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE  sqlConditon=' '
	// * and (cd.func in
	// * ('trace_routes_selectCalendar','trace_routes_selectDirect','
	// * trace_routes_selectCycutoffCalendar','
	// * trace_routes_selectArrivalCalendar','
	// * trace_routes_selectDepartureCalendar','trace_routes_changeTransitTime
	// * ') or cd.func like 'trace_routes_filter%');
	// */
	// String sql = "SELECT * FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE
	// "+sqlConditon+"='" + id
	// + "' and (cd.func in
	// ('trace_routes_selectCalendar','trace_routes_selectDirect','trace_routes_selectCycutoffCalendar','trace_routes_selectArrivalCalendar','trace_routes_selectDepartureCalendar','trace_routes_changeTransitTime')
	// or cd.func like 'trace_routes_filter%') "
	// + EXCLUDE_INTERNAL_USER;
	// List<Clientusagedata> part1 = resultStepByStep(sql);
	// return part1;
	// }
}
