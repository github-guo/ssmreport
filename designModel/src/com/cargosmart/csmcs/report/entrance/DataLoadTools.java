package com.cargosmart.csmcs.report.entrance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cargosmart.csmcs.report.common.DateUtil;
import com.cargosmart.csmcs.report.db.Configure;
import com.cargosmart.csmcs.report.db.DBHelper;
import com.cargosmart.csmcs.report.db.DBSources;
import com.cargosmart.csmcs.report.domain.Clientusagedata;
import com.cargosmart.csmcs.report.domain.SearchDetailObject;

public class DataLoadTools {

	private String SQL_FIND_REGIST_USERID = "";
	private String SQL_FIND_PUBLISH_USERIP = "";
	private String SQL_FUNC_CODE="";
	private String sqlConditon = "cd.userIdentification#userID";
	private DBHelper dbHelper;
	private static Logger logger = Logger.getLogger(DataLoadTools.class);
	DBSources dbSources ;

	public DataLoadTools() {
		Configure config = new Configure("config.properties");
		SQL_FIND_PUBLISH_USERIP = config.getValue("SQL_FIND_PUBLISH_USERIP");
		SQL_FIND_REGIST_USERID = config.getValue("SQL_FIND_REGIST_USERID");
		SQL_FUNC_CODE=config.getValue("SQL_FUNC_CODE");
		dbHelper = new DBHelper();
		dbSources=new DBSources(null);
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
		DateUtil dateUtil = new DateUtil();
		String sql = "SELECT * FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE "+ sqlConditon + " ='" + id
				+ "'"+ " and createTime between convert(datetime,'"+dateUtil.getStartTime()+"') and convert(datetime,'"+dateUtil.getEndTime()+"') ORDER BY cd.createTime";
		logger.debug(sql);
//		String sql = "SELECT * FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE " + sqlConditon + " ='" + id
//				+ "'"+SQL_FUNC_CODE+ " and createTime between convert(datetime,'"+DateUtil.getStartTime()+"') and convert(datetime,'"+DateUtil.getEndTime()+"') ORDER BY cd.createTime";
		List<Clientusagedata> searchRecords = resultStepByStep(sql);
		return searchRecords;

	}

	public void setSearchByIP(boolean byIP) {
		if (byIP == true) {
			sqlConditon = "LEN(cd.userIdentification#userID)=0  and cd.requestInformation#requestIp";
		}
	}

	// Customer Segment refer to CSSOWNER.SSM_FREEMIUM_USERS.companyType
	public String getSearchSegment(String userID) {
		if("".equals(userID)||userID==null)
			return "";
		Connection connection = dbHelper.getConnection();
		String sql ="SELECT companyType  FROM CSSOWNER.SSM_FREEMIUM_USERS where emailAddress=?";
		try {
			PreparedStatement pre=connection.prepareStatement(sql);
			pre.setString(1, userID);
			ResultSet rs=pre.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}else {
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	public void writeSearchDetails(List<SearchDetailObject> searchDetailList) {
		Connection connection=dbSources.getConnection();
//		INSERT INTO [CSMCS].[CSSOWNER].[searchDetail] ([search_journey], [customer_type], [customer_segment], [search_method], [search_platform], [userID], [ip], [updateDate]) VALUES ('sdfsdf', 'sdf234', 'asdf', 'asdf', 'asdf', 'asdfa', 'sdf', '2015-11-17 10:38:49')
//		String sql = "INSERT INTO `searchDetail` (`search_journey`, `customer_type`, `customer_segment`, `search_method`, `search_platform`, `userID`, `ip`, `search_date`, `updateDate`) VALUES (?,?,?,?,?,?,?,?,?)";
		String sql="INSERT INTO [CSMCS].[CSSOWNER].[searchDetail] ([search_journey], [customer_type], [customer_segment], [search_method], [search_platform], [userID], [ip],[search_date], [updateDate],[_id]) VALUES (?,?,?,?,?,?,?,?,?,?)";
		for(SearchDetailObject per:searchDetailList){
			try {
				PreparedStatement pre=connection.prepareStatement(sql);
				pre.setString(1, per.getSearchJourney());
				pre.setString(2, per.getCustomerType());
				pre.setString(3, per.getCustomerSegment());
				pre.setString(4, per.getSearchMethod());
				pre.setString(5, per.getSearchPlatform());
				pre.setString(6, per.getUserId());
				pre.setString(7, per.getIp());
				pre.setTimestamp(8,new Timestamp(per.getSearchDate().getTime()));
				pre.setTimestamp(9, new Timestamp(new Date().getTime()));
				pre.setString(10, per.getId());
				pre.execute();
			} catch (SQLException e) {
				logger.error("duplicate search record:"+per.getId(), e);
				continue;
			}
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
