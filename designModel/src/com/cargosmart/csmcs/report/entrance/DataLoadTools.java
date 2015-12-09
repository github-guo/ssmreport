package com.cargosmart.csmcs.report.entrance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private String SQL_FUNC_CODE = "";
	private String UPDATE_SEARCH_RESULT = "";
	private String sqlConditon = "cd.userIdentification#userID";
	private String INSERT_SEARCH_RESULT_SQL = "";
	private DBHelper dbHelper;
	private static Logger logger = Logger.getLogger(DataLoadTools.class);
	DBSources dbSources;

	public DataLoadTools() {
		Configure config = new Configure("config.properties");
		SQL_FIND_PUBLISH_USERIP = config.getValue("SQL_FIND_PUBLISH_USERIP");
		SQL_FIND_REGIST_USERID = config.getValue("SQL_FIND_REGIST_USERID");
		SQL_FUNC_CODE = config.getValue("SQL_FUNC_CODE");
		INSERT_SEARCH_RESULT_SQL = config.getValue("INSERT_SEARCH_RESULT");
		UPDATE_SEARCH_RESULT = config.getValue("UPDATE_SEARCH_RESULT");
		dbHelper = new DBHelper();
		dbSources = new DBSources(null);
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
			while (rs != null && rs.next() != false) {
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		Calendar startCalendar = Calendar.getInstance();
		Calendar currentCalendar = Calendar.getInstance();
		
		try {
			startCalendar.setTime(sdf.parse(dateUtil.getStartTime()));
			if(currentCalendar.before(startCalendar)){
				logger.info("skip process "+id);
				return new ArrayList<>();
			}
		} catch (ParseException e) {
			logger.error("please check your config.properties: startTime format must be startTime=yyyy-MM-dd hh:mm:ss:SSS",e);
		}
		logger.info("processing user id or ip is " + id);
		String sql = "SELECT * FROM CSSOWNER.CLIENTUSAGEDATAS AS cd WHERE " + sqlConditon + " ='" + id + "'"
				+ " and createTime between convert(datetime,'" + dateUtil.getStartTime() + "') and convert(datetime,'"
				+ dateUtil.getEndTime() + "') ORDER BY cd.createTime";
		logger.debug(sql);
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
		if ("".equals(userID) || userID == null)
			return "";
		Connection connection = dbHelper.getConnection();
		String sql = "SELECT companyType  FROM CSSOWNER.SSM_FREEMIUM_USERS where emailAddress=?";
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setString(1, userID);
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			} else {
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	public void writeSearchDetails(List<SearchDetailObject> searchDetailList) {
		Connection connection = dbSources.getConnection();
		String sql = INSERT_SEARCH_RESULT_SQL;

		try {
			connection.setAutoCommit(false);
			for (SearchDetailObject per : searchDetailList) {
				try {
					PreparedStatement pre = connection.prepareStatement(sql);
					pre.setString(1, per.getSearchJourney());
					pre.setString(2, per.getCustomerType());
					pre.setString(3, per.getCustomerSegment());
					pre.setString(4, per.getSearchMethod());
					pre.setString(5, per.getSearchPlatform());
					pre.setString(6, per.getUserId());
					pre.setString(7, per.getIp());
					pre.setTimestamp(8, new Timestamp(per.getSearchDate().getTime()));
					pre.setTimestamp(9, new Timestamp(new Date().getTime()));
					pre.setString(10, per.getId());
					pre.execute();
				} catch (SQLException e) {
					String updateSql = UPDATE_SEARCH_RESULT;
					try {
						PreparedStatement pre = connection.prepareStatement(updateSql);
						pre.setString(1, per.getCustomerSegment());
						pre.setString(2, per.getSearchJourney());
						pre.setString(3, per.getCustomerType());
						pre.setString(4, per.getSearchMethod());
						pre.setString(5, per.getSearchPlatform());
						pre.setString(6, per.getUserId());
						pre.setString(7, per.getIp());
						pre.setTimestamp(8, new Timestamp(new Date().getTime()));
						pre.setString(9, per.getId());
						pre.execute();
					} catch (SQLException e1) {
						logger.error("can not update search record ,id:" + per.getId(), e1);
						connection.rollback();
						continue;
					}
					logger.info("update search_usage_analysis,duplicate search record id:" + per.getId());
					continue;
				}
			}
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertTopUsage(Map<String, Integer> topUserCount) {
		Connection connection = dbSources.getConnection();
		String sql = "INSERT INTO [CSMCS].[STAGE].[BS_TOP_Usage] ([UserID], [search_count],[updateDate]) VALUES (?, ?,?)";
		logger.info("begin insert into bs_top_usage");
		try {
			connection.setAutoCommit(false);
			for (String uid : topUserCount.keySet()) {
				int number = 0;
				try {
					
					number = topUserCount.get(uid);
					PreparedStatement pre = connection.prepareStatement(sql);
					pre.setString(1, uid);
					pre.setInt(2, number);
					pre.setTimestamp(3, new Timestamp(new Date().getTime()));
					pre.execute();
					logger.info("insert into bs_top_usage,uid"+uid);
				} catch (SQLException e) {
					logger.info("update bs_top_usage,uid:"+uid);
					String updatesql = "UPDATE [CSMCS].[STAGE].[BS_TOP_Usage] SET [search_count]=?, [updateDate]=? WHERE ([UserID]=?)";
					PreparedStatement pre = connection.prepareStatement(updatesql);
					pre.setInt(1, number);
					pre.setTimestamp(2, new Timestamp(new Date().getTime()));
					pre.setString(3, uid);
					pre.execute();
				}
			}
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error("insert bs_top_usage fail,rollback");
				e1.printStackTrace();
			}
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("fail to close connection");
				e.printStackTrace();
			}
		}
	}

	public int getTopSumCount(int i) {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(rs.search_count)from (select top ");
		sb.append(i);
		sb.append(" search_count from STAGE.BS_TOP_Usage order by search_count desc) rs");
		String sql=sb.toString();
		Connection connection = dbSources.getConnection();
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			ResultSet rs=pre.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void save2usageRange(String group, int top, float per) {
		String sql ="INSERT INTO [CSMCS].[STAGE].[BS_TOP_USAGE_RANK] ([Group], [total_search_count], [Percentage], [updateDate]) VALUES (?,?,?,?)";
		
		Connection connection = dbSources.getConnection();
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setString(1, group);
			pre.setInt(2, top);
			pre.setFloat(3, per);
			pre.setTimestamp(4, new Timestamp(new Date().getTime()));
			pre.execute();
			logger.info("insert into BS_TOP_USAGE_RANK");
		} catch (SQLException e) {
			String updateSql="UPDATE [CSMCS].[STAGE].[BS_TOP_USAGE_RANK] SET [total_search_count]=?, [Percentage]=?, [updateDate]=? WHERE ([Group]=?)";
			try {
				PreparedStatement pre = connection.prepareStatement(updateSql);
				pre.setInt(1, top);
				pre.setFloat(2, per);
				pre.setTimestamp(3, new Timestamp(new Date().getTime()));
				pre.setString(4, group);
				pre.execute();
				logger.info("update  BS_TOP_USAGE_RANK");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public int copyUsageAnalysis2TopUsage() {
		String sql ="select ua.userID,COUNT(1) from STAGE.SEARCH_USAGE_ANALYSIS ua where ua.customer_type='Registered Users' and not exists(select 1 from POI.BS_INTERNAL_USE_LIST u where upper(ua.userID)=upper(u.[OFFICE EMAIL ADDRESS])) GROUP BY ua.userID order by COUNT(1) desc";
		Connection connection = dbSources.getConnection();
		int sum =0;
		Map<String, Integer> userSearchCount=new HashMap<>();
		try {
			PreparedStatement pre =connection.prepareStatement(sql);
			ResultSet rs=pre.executeQuery();
			while(rs.next()){
				int count=rs.getInt(2);
				userSearchCount.put(rs.getString(1), count);
				sum+=count;
			}
			insertTopUsage(userSearchCount);
			connection.close();
			return sum;
		} catch (SQLException e) {
			logger.error("can not get users list from search_usage_analysis,sql:\n"+sql+"\n",e);
			return 0;
		}
		
	}
}
