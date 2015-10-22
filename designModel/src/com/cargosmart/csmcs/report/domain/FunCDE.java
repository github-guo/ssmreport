package com.cargosmart.csmcs.report.domain;

public enum FunCDE {
	 TRACE_ROUTES_SEARCH("trace_routes_search")
	,TRACE_MAIN_ROUTES_SEARCH("trace_main_routes_search")
	,TRACE_ROUTES_FAVORITE_ITEM("trace_routes_favorite_item")
	,NEXT_ACTION_IS_ANOTHER_SEARCH("next action is another search")
	,_OR_FUNC_CODE_IS_TRACE_ROUTES_FIRSTTHINGAFTERSEARCH("or func code is trace_routes_firstThingAfterSearch")
	,TRACE_ROUTES_CLICKSSRRIMAGE("trace_routes_clickSSRRImage")
	,TRACE_ROUTES_SELECTSSRRPORTPAIR("trace_routes_selectSSRRPortPair")
	,TRACE_ROUTES_SELECTDETAIL("trace_routes_selectDetail")
	,TRACE_ROUTES_FILTER("trace_routes_filter*")
	,TRACE_ROUTES_SELECTCALENDAR("trace_routes_selectCalendar")
	,TRACE_ROUTES_SELECTDIRECT("trace_routes_selectDirect")
	,TRACE_ROUTES_SELECTCYCUTOFFCALENDAR("trace_routes_selectCycutoffCalendar")
	,TRACE_ROUTES_SELECTARRIVALCALENDAR("trace_routes_selectArrivalCalendar")
	,TRACE_ROUTES_SELECTDEPARTURECALENDAR("trace_routes_selectDepartureCalendar")
	,TRACE_ROUTES_CHANGETRANSITTIME("trace_routes_changeTransitTime");
	
	private String description;

	@Override
	public String toString() {
		return description;
	}
	
    FunCDE(String description) {
		this.description= description;
	}
    public static void main(String[] args) {
		System.out.println(FunCDE.TRACE_ROUTES_FILTER);
	}
}
