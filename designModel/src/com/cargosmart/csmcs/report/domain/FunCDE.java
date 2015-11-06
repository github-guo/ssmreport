package com.cargosmart.csmcs.report.domain;

public enum FunCDE {
	 TRACE_ROUTES_SEARCH("trace_routes_search")
	,TRACE_MAIN_ROUTES_SEARCH("trace_main_routes_search")
	,TRACE_MAIN_SEARCH("trace_main_search")
	,TRACE_ROUTES_SEARCH_PUBLIC("trace_routes_search_public")
	,TRACE_ROUTES_FAVORITE_ITEM("trace_routes_favorite_item")
	,TRACE_MAIN_FAVORITE_ITEM("trace_main_favorite_item")
	,TRACE_ROUTES_FOCUSROUTES("trace_routes_focusRoutes")
	,TRACE_ROUTES_FIRSTTHINGAFTERSEARCH("trace_routes_firstThingAfterSearch")
	,TRACE_ROUTES_CLICKSSRRIMAGE("trace_routes_clickSSRRImage")
	,TRACE_ROUTES_SELECTSSRRPORTPAIR("trace_routes_selectSSRRPortPair")
	,TRACE_ROUTES_SELECTDETAIL("TRACE_ROUTES_SELECTDETAIL")
	,TRACE_ROUTES_SELECTCALENDAR("trace_routes_selectCalendar")
	,TRACE_ROUTES_SELECTDIRECT("trace_routes_selectDirect")
	,TRACE_ROUTES_SELECTCYCUTOFFCALENDAR("trace_routes_selectCycutoffCalendar")
	,TRACE_ROUTES_SELECTARRIVALCALENDAR("trace_routes_selectArrivalCalendar")
	,TRACE_ROUTES_SELECTDEPARTURECALENDAR("trace_routes_selectDepartureCalendar")
	,TRACE_COMMON_CLICK_SIGNUP("trace_common_click_signup")
	,TRACE_ROUTES_CHANGETRANSITTIME("trace_routes_changeTransitTime");
	
	private String description;

	@Override
	public String toString() {
		return description;
	}
	
    FunCDE(String description) {
		this.description= description;
	}
    
}
