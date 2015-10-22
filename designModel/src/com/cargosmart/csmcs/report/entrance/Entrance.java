package com.cargosmart.csmcs.report.entrance;

import java.util.Calendar;
import java.util.List;

import com.cargosmart.csmcs.report.domain.Clientusagedata;


public class Entrance {
//	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	private DataLoadTools dataLoadTools = new DataLoadTools();
	
	private long manual_searchWithoutFurtherAction =0;
	private long manual_searchReliability=0;
	private long manual_showMap=0;
	private long manual_showMap_refineSearch=0;
	private long manual_showMap_scheduleReliability=0;
	private long manual_showMap_refineSearch_scheduleReliability=0;
	private long favorite_searchWithoutFurtherAction =0;
	private long favorite_searchReliability=0;
	private long favorite_showMap=0;
	private long favorite_showMap_refineSearch=0;
	private long favorite_showMap_scheduleReliability=0;
	private long favorite_showMap_refineSearch_scheduleReliability=0;
	private long manual_others=0;
	private long favorite_others=0;
	private long searchWithoutFurtherAction_web_channel=0;
	private long searchWithoutFurtherAction_mobile_channel=0;
	private long searchReliability_web_channel=0;
	private long searchReliability_mobile_channel=0;
	private long searchshowMap_web_channel=0;
	private long searchshowMap_mobile_channel=0;
	private long showMap_refineSearch_web_channel=0;
	private long showMap_refineSearch_mobile_channel=0;
	private long showMap_scheduleReliability_web_channel=0;
	private long showMap_scheduleReliability_mobile_channel=0;
	private long showMap_refineSearch_scheduleReliability_web_channel=0;
	private long showMap_refineSearch_scheduleReliability_mobile_channel=0;
	
	public void first(){
		List<String> registerUserIDs=dataLoadTools.getRegistUserIDs();
//		List<String> publishUsersIDs=dataLoadTools.getPublishUserIDs();
		byManualSearch(registerUserIDs);
		byFavouriteSearch(registerUserIDs);
		dataLoadTools.setSearchByIP(true);
//		byManualSearch(publishUsersIDs);
		System.out.println("by manual/favorite");
		System.out.println("manual_searchWithoutFurtherAction:"+manual_searchWithoutFurtherAction);
		System.out.println("manual_searchReliability:"+manual_searchReliability);
		System.out.println("manual_showMap:"+manual_showMap);
		System.out.println("manual_showMap_refineSearch:"+manual_showMap_refineSearch);
		System.out.println("manual_showMap_scheduleReliability:"+manual_showMap_scheduleReliability);
		System.out.println("manual_showMap_refineSearch_scheduleReliability:"+manual_showMap_refineSearch_scheduleReliability);
		System.out.println("favorite_searchWithoutFurtherAction:"+favorite_searchWithoutFurtherAction);
		System.out.println("favorite_searchReliability"+favorite_searchReliability);
		System.out.println("favorite_showMap"+favorite_showMap);
		System.out.println("favorite_showMap_refineSearch:"+favorite_showMap_refineSearch);
		System.out.println("favorite_showMap_scheduleReliability:"+favorite_showMap_scheduleReliability);
		System.out.println(favorite_showMap_refineSearch_scheduleReliability);
		System.out.println(favorite_others);
	}
	
	public static void main(String[] args) {
		new Entrance().first();
	}
	
	
	public void byManualSearch(List<String>registerUserIDs ){
		for(String id:registerUserIDs){
			System.out.println(id);
			//load the steps data by user id 
			List<Clientusagedata> manaualSearch=dataLoadTools.manualSearch(id);
			List<Clientusagedata> WithoutFurtherAction=dataLoadTools.WithoutFurtherAction(id);
			List<Clientusagedata> RefineSearch=dataLoadTools.RefineSearch(id);
			List<Clientusagedata> ScheduleReliability=dataLoadTools.ScheduleReliability(id);
			List<Clientusagedata> ShowMap=dataLoadTools.ShowMap(id);
//			List<Clientusagedata> favoriteSearch=dataLoadTools.favoriteSearch(id);
			System.out.println("working");
			for(int i=0;i<manaualSearch.size();i++){
				
				boolean isSearchReliability= false;
				boolean isShowmap=false;
				boolean isRefineSearch=false;
				Clientusagedata record = manaualSearch.get(i);
				Clientusagedata nextSearchRecord = null ;
				if(i!=manaualSearch.size()-1){
					nextSearchRecord = manaualSearch.get(i+1);
					System.out.println("nextSearchRecord.getCreateTime()==="+nextSearchRecord.getCreateTime());
				}
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(record.getCreateTime());
				calendar.add(Calendar.MINUTE, 30);
//				String now =sdf.format(calendar.getTime());
				
				
				
				if(ScheduleReliability!=null&&ScheduleReliability.size()>0){
					for(Clientusagedata ScheduleReliabilityRecord:ScheduleReliability){
						System.out.println("after add 30 min"+calendar.getTime());
						if(record.getCreateTime().before(ScheduleReliabilityRecord.getCreateTime())&&calendar.getTime().after(ScheduleReliabilityRecord.getCreateTime())){
							if(nextSearchRecord!=null&&nextSearchRecord.getCreateTime().after(ScheduleReliabilityRecord.getCreateTime())){
								isSearchReliability=true;
								ScheduleReliability.remove(ScheduleReliabilityRecord);
								break;
							}else if(nextSearchRecord==null){
								isSearchReliability=true;
								ScheduleReliability.remove(ScheduleReliabilityRecord);
								break;
							}
						}
					}
				}
				
				if(ShowMap!=null&&ShowMap.size()>0){
					for(Clientusagedata showmap:ShowMap){
						if(record.getCreateTime().before(showmap.getCreateTime())&&calendar.getTime().after(showmap.getCreateTime())){
				
							if(nextSearchRecord!=null&&nextSearchRecord.getCreateTime().after(showmap.getCreateTime())){
								isShowmap=true;
								ScheduleReliability.remove(showmap);
								break;
							}else if(nextSearchRecord==null){
								isShowmap=true;
								ScheduleReliability.remove(showmap);
								break;
							}
						}
					}
				}
				
				if(RefineSearch!=null&&RefineSearch.size()>0){
					for(Clientusagedata refineSearch:RefineSearch){
						if(record.getCreateTime().before(refineSearch.getCreateTime())&&calendar.getTime().after(refineSearch.getCreateTime())){
						
							if(nextSearchRecord!=null&&nextSearchRecord.getCreateTime().after(refineSearch.getCreateTime())){
								isRefineSearch=true;
								RefineSearch.remove(refineSearch);
								break;
							}else if(nextSearchRecord==null){
								isRefineSearch=true;
								RefineSearch.remove(refineSearch);
								break;
							}
						}
					}
				}
				
				
				if(WithoutFurtherAction!=null&&WithoutFurtherAction.size()>0){
					
					for(Clientusagedata WithoutFurtherActionRecord:WithoutFurtherAction){
						if(record.getCreateTime().before(WithoutFurtherActionRecord.getCreateTime())&&calendar.getTime().after(WithoutFurtherActionRecord.getCreateTime())){
							if(nextSearchRecord!=null&&nextSearchRecord.getCreateTime().after(WithoutFurtherActionRecord.getCreateTime())){
								if(isRefineSearch||isSearchReliability||isShowmap){
									WithoutFurtherAction.remove(WithoutFurtherActionRecord);
									break;
								}
								manual_searchWithoutFurtherAction++;
								WithoutFurtherAction.remove(WithoutFurtherActionRecord);
								break;
							}
						}						
					}
				}
				
				if(isSearchReliability==true&&isShowmap==false&&isSearchReliability==false){
					manual_searchReliability++;
				}else if(isShowmap==true&&isRefineSearch==false&&isSearchReliability==false) {
					manual_showMap++;
				}else if(isShowmap==true&&isRefineSearch==true&&isSearchReliability==false){
					manual_showMap_refineSearch++;
				}else if(isShowmap==true&&isSearchReliability==true&&isRefineSearch==false){
					manual_showMap_scheduleReliability++;
				}else if(isShowmap==true&&isRefineSearch==true&&isSearchReliability==true){
					manual_showMap_refineSearch_scheduleReliability++;
				}else {
					manual_others++;
				}
				
			}
		}
	}
	
	
	public void byFavouriteSearch(List<String>registerUserIDs ){

		for(String id:registerUserIDs){
			System.out.println(id);
			//load the steps data by user id 
			List<Clientusagedata> favoriteSearch=dataLoadTools.favoriteSearch(id);
			List<Clientusagedata> WithoutFurtherAction=dataLoadTools.WithoutFurtherAction(id);
			List<Clientusagedata> RefineSearch=dataLoadTools.RefineSearch(id);
			List<Clientusagedata> ScheduleReliability=dataLoadTools.ScheduleReliability(id);
			List<Clientusagedata> ShowMap=dataLoadTools.ShowMap(id);
//			List<Clientusagedata> favoriteSearch=dataLoadTools.favoriteSearch(id);
			System.out.println("working");
			for(Clientusagedata record:favoriteSearch){
				
				boolean isSearchReliability= false;
				boolean isShowmap=false;
				boolean isRefineSearch=false;
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(record.getCreateTime());
				calendar.add(Calendar.MINUTE, 30);
//				String now =sdf.format(calendar.getTime());
				
				if(WithoutFurtherAction!=null&&WithoutFurtherAction.size()>0){
					for(Clientusagedata WithoutFurtherActionRecord:WithoutFurtherAction){
						if(record.getCreateTime().before(WithoutFurtherActionRecord.getCreateTime())&&calendar.getTime().after(WithoutFurtherActionRecord.getCreateTime())){
							WithoutFurtherAction.remove(WithoutFurtherActionRecord);
							manual_searchWithoutFurtherAction++;
							break;
						}						
					}
					continue;
				}
				
				if(ScheduleReliability!=null&&ScheduleReliability.size()>0){
					for(Clientusagedata ScheduleReliabilityRecord:ScheduleReliability){
						if(record.getCreateTime().before(ScheduleReliabilityRecord.getCreateTime())&&calendar.getTime().after(ScheduleReliabilityRecord.getCreateTime())){
							isSearchReliability=true;
							break;
						}
					}
				}
				
				if(ShowMap!=null&&ShowMap.size()>0){
					for(Clientusagedata showmap:ShowMap){
						if(record.getCreateTime().before(showmap.getCreateTime())&&calendar.getTime().after(showmap.getCreateTime())){
							isShowmap=true;
							break;
						}
					}
				}
				
				if(RefineSearch!=null&&RefineSearch.size()>0){
					for(Clientusagedata refineSearch:RefineSearch){
						if(record.getCreateTime().before(refineSearch.getCreateTime())&&calendar.getTime().after(refineSearch.getCreateTime())){
							isRefineSearch=true;
							break;
						}
					}
				}
				
				if(isSearchReliability==true&&isShowmap==false&&isSearchReliability==false){
					favorite_searchReliability++;
				}else if(isShowmap==true&&isRefineSearch==false&&isSearchReliability==false) {
					favorite_showMap++;
				}else if(isShowmap==true&&isRefineSearch==true&&isSearchReliability==false){
					favorite_showMap_refineSearch++;
				}else if(isShowmap==true&&isSearchReliability==true&&isRefineSearch==false){
					favorite_showMap_scheduleReliability++;
				}else if(isShowmap==true&&isRefineSearch==true&&isSearchReliability==true){
					favorite_showMap_refineSearch_scheduleReliability++;
				}else {
					favorite_others++;
				}
				
			}
		}
	
	}
	
}
