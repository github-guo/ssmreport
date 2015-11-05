package com.cargosmart.csmcs.report.entrance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cargosmart.csmcs.report.common.DateUtil;
import com.cargosmart.csmcs.report.db.Configure;
import com.cargosmart.csmcs.report.db.DBHelper;
import com.cargosmart.csmcs.report.domain.Clientusagedata;

public class DataLoadTools {

	private String SQL_FIND_REGIST_USERID = "";
	private String SQL_FIND_PUBLISH_USERIP = "";
	private String SQL_FUNC_CODE="";
	private String sqlConditon = "cd.userIdentification#userID";
	private DBHelper dbHelper;
	private static Logger logger = Logger.getLogger(DataLoadTools.class);

	public DataLoadTools() {
		Configure config = new Configure("config.properties");
		SQL_FIND_PUBLISH_USERIP = config.getValue("SQL_FIND_PUBLISH_USERIP");
		SQL_FIND_REGIST_USERID = config.getValue("SQL_FIND_REGIST_USERID");
		SQL_FUNC_CODE=config.getValue("SQL_FUNC_CODE");
		dbHelper = new DBHelper();
		dbHelper.loadConfigure(config);
	}

	private List<String> getUserWithCondition(String sql) {

		ResultSet rs = dbHelper.excuteQuery(sql);

		List<String> registerIDs = new ArrayList<>();
		try {
			while (rs.next() != false) {
				String id = rs.getString(1);
				registerIDs.add(id);
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

	public List<String> getPublishUserIPs() {
		logger.info("getPublishUserIDs start");
		return getUserWithCondition(SQL_FIND_PUBLISH_USERIP);
	}

	private List<Clientusagedata> resultStepByStep(String sql) {
		List<Clientusagedata> searchRs = new ArrayList<>();
		long begin = System.currentTimeMillis();
		ResultSet rs = dbHelper.excuteQuery(sql);
		try {
			Clientusagedata obj = null;
			while (rs!=null&&rs.next()!= false) {
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
			e.printStackTrace();
			System.err.println("can not get result from db");
		}
		long end = System.currentTimeMillis();
		if ((end - begin) >= 3000) {
			logger.info("sql :" + sql + "\n" + " execute time:" + (end - begin));
		}
		return searchRs;
	}

	public List<Clientusagedata> allSearch(String id) {
		String sql = "SELECT * FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE " + sqlConditon + " ='" + id
				+ "'"+ " and createTime between convert(datetime,'"+DateUtil.getStartTime()+"') and convert(datetime,'"+DateUtil.getEndTime()+"') ORDER BY cd.createTime";
//		String sql = "SELECT * FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE " + sqlConditon + " ='" + id
//				+ "'"+SQL_FUNC_CODE+ " and createTime between convert(datetime,'"+DateUtil.getStartTime()+"') and convert(datetime,'"+DateUtil.getEndTime()+"') ORDER BY cd.createTime";
		List<Clientusagedata> searchRecords = resultStepByStep(sql);
		return searchRecords;

	}

	public void setSearchByIP(boolean byIP) {
		if (byIP == true) {
			sqlConditon = "cd.requestInformation#requestIp";
		}
	}
}
