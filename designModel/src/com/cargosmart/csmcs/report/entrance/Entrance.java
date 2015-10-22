package com.cargosmart.csmcs.report.entrance;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.cargosmart.csmcs.report.domain.Clientusagedata;

public class Entrance {

	private Logger logger = Logger.getLogger(this.getClass());

	// private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
	// hh:mm:ss");

	private DataLoadTools dataLoadTools = new DataLoadTools();

	private long manual_searchWithoutFurtherAction = 0;
	private long manual_searchReliability = 0;
	private long manual_showMap = 0;
	private long manual_showMap_refineSearch = 0;
	private long manual_showMap_scheduleReliability = 0;
	private long manual_showMap_refineSearch_scheduleReliability = 0;
	private long favorite_searchWithoutFurtherAction = 0;
	private long favorite_searchReliability = 0;
	private long favorite_showMap = 0;
	private long favorite_showMap_refineSearch = 0;
	private long favorite_showMap_scheduleReliability = 0;
	private long favorite_showMap_refineSearch_scheduleReliability = 0;
	private long favorite_others = 0;
	private long manual_others = 0;
	private long favorite_refineSearch = 0;
	private long manual_refineSearch = 0;

	private long manual_searchReliability_mobile;

	private long manual_searchReliability_web;

	private long manual_showMap_mobile;

	private long manual_showMap_web;

	private long manual_showMap_refineSearch_mobile;

	private long manual_showMap_refineSearch_web;

	private long manual_showMap_scheduleReliability_mobile;

	private long manual_showMap_scheduleReliability_web;

	private long manual_showMap_refineSearch_scheduleReliability_mobile;

	private long manual_showMap_refineSearch_scheduleReliability_web;

	private long manual_refineSearch_mobile;

	private long manual_refineSearch_web;

	private long manual_searchWithoutFurtherAction_mobile;

	private long manual_searchWithoutFurtherAction_web;

	private long manual_others_mobile;

	private long manual_others_web;

	private int favorite_searchReliability_mobile;

	private int favorite_searchReliability_web;

	private int favorite_showMap_mobile;

	private int favorite_showMap_web;

	private int favorite_showMap_refineSearch_mobile;

	private int favorite_showMap_refineSearch_web;

	private int favorite_showMap_scheduleReliability_mobile;

	private int favorite_showMap_scheduleReliability_web;

	private int favorite_showMap_refineSearch_scheduleReliability_mobile;

	private int favorite_showMap_refineSearch_scheduleReliability_web;

	private int favorite_refineSearch_mobile;

	private int favorite_refineSearch_web;

	private int favorite_searchWithoutFurtherAction_mobile;

	private int favorite_searchWithoutFurtherAction_web;

	private int favorite_others_mobile;

	private int favorite_others_web;

	public void first() {
		List<String> registerUserIDs = dataLoadTools.getRegistUserIDs();
		// List<String> publishUsersIDs=dataLoadTools.getPublishUserIDs();
		byManualSearch(registerUserIDs);
		byFavoriteSearch(registerUserIDs);
		// dataLoadTools.setSearchByIP(true);
		// byManualSearch(publishUsersIDs);
		
		
		//by manual search
		System.out.println("by manual/favorite");
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
		System.out.println("favorite_others_web:"+favorite_others_web);
		
	}

	public static void main(String[] args) {
		new Entrance().first();
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
					manual_searchReliability++;
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						manual_searchReliability_mobile ++;
					}else{
						manual_searchReliability_web++;
					}
				} else if (isShowmap == true && isRefineSearch == false && isSearchReliability == false) {
					manual_showMap++;
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						manual_showMap_mobile ++;
					}else{
						manual_showMap_web++;
					}
				} else if (isShowmap == true && isRefineSearch == true && isSearchReliability == false) {
					manual_showMap_refineSearch++;
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						manual_showMap_refineSearch_mobile ++;
					}else{
						manual_showMap_refineSearch_web++;
					}
				} else if (isShowmap == true && isSearchReliability == true && isRefineSearch == false) {
					manual_showMap_scheduleReliability++;
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						manual_showMap_scheduleReliability_mobile ++;
					}else{
						manual_showMap_scheduleReliability_web++;
					}
				} else if (isShowmap == true && isRefineSearch == true && isSearchReliability == true) {
					manual_showMap_refineSearch_scheduleReliability++;
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						manual_showMap_refineSearch_scheduleReliability_mobile ++;
					}else{
						manual_showMap_refineSearch_scheduleReliability_web++;
					}
				} else if (isRefineSearch == true && isShowmap == false && isSearchReliability == false) {
					manual_refineSearch++;
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						manual_refineSearch_mobile ++;
					}else{
						manual_refineSearch_web++;
					}
				} else if (isWithoutFurtherAction == true && isRefineSearch == false && isSearchReliability == false
						&& isShowmap == false) {
					manual_searchWithoutFurtherAction++;
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						manual_searchWithoutFurtherAction_mobile ++;
					}else{
						manual_searchWithoutFurtherAction_web++;
					}
				} else {
					manual_others++;
					// channel web or mobile
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						manual_others_mobile ++;
					}else{
						manual_others_web++;
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
					favorite_searchReliability++;
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						favorite_searchReliability_mobile ++;
					}else{
						favorite_searchReliability_web++;
					}
				} else if (isShowmap == true && isRefineSearch == false && isSearchReliability == false) {
					favorite_showMap++;
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						favorite_showMap_mobile ++;
					}else{
						favorite_showMap_web++;
					}
				} else if (isShowmap == true && isRefineSearch == true && isSearchReliability == false) {
					favorite_showMap_refineSearch++;
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						favorite_showMap_refineSearch_mobile ++;
					}else{
						favorite_showMap_refineSearch_web++;
					}
				} else if (isShowmap == true && isSearchReliability == true && isRefineSearch == false) {
					favorite_showMap_scheduleReliability++;
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						favorite_showMap_scheduleReliability_mobile ++;
					}else{
						favorite_showMap_scheduleReliability_web++;
					}
				} else if (isShowmap == true && isRefineSearch == true && isSearchReliability == true) {
					favorite_showMap_refineSearch_scheduleReliability++;
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						favorite_showMap_refineSearch_scheduleReliability_mobile ++;
					}else{
						favorite_showMap_refineSearch_scheduleReliability_web++;
					}
				} else if (isRefineSearch == true && isShowmap == false && isSearchReliability == false) {
					favorite_refineSearch++;
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						favorite_refineSearch_mobile ++;
					}else{
						favorite_refineSearch_web++;
					}
				} else if (isWithoutFurtherAction == true && isRefineSearch == false && isSearchReliability == false
						&& isShowmap == false) {
					favorite_searchWithoutFurtherAction++;
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						favorite_searchWithoutFurtherAction_mobile ++;
					}else{
						favorite_searchWithoutFurtherAction_web++;
					}
				} else {
					favorite_others++;
					if(firstSearchRecord.getRequestInformation_requestHeaders_user_agent().matches("^Big Schedules.*iPhone' and '^Apache-HttpClient")){
						favorite_others_mobile ++;
					}else{
						favorite_others_web++;
					}
				}

			}
		}
	}

}
