package com.cargosmart.csmcs.report.common;

import java.util.ArrayList;
import java.util.List;

import com.cargosmart.csmcs.report.domain.FunCDE;

public class StepFunMapping {

	public static List<FunCDE> manual_search;
	public static List<FunCDE> favorite_search;
	public static List<FunCDE> without_further_action;
	public static List<FunCDE> schedule_reliability;
	public static List<FunCDE> show_map;
	public static List<FunCDE> refine_search;
	public static List<FunCDE> searchRegist;

	static {
		manual_search = new ArrayList<>();
		favorite_search = new ArrayList<>();
		without_further_action = new ArrayList<>();
		schedule_reliability = new ArrayList<>();
		show_map = new ArrayList<>();
		refine_search = new ArrayList<>();
		searchRegist =new ArrayList<>();
		manual_search();
		favorite_search();
		without_further_action();
		schedule_reliability();
		show_map();
		refine_search();
		search_regist();
	}

	private static void manual_search() {
		manual_search.add(FunCDE.TRACE_ROUTES_SEARCH);
		manual_search.add(FunCDE.TRACE_MAIN_ROUTES_SEARCH);
		manual_search.add(FunCDE.TRACE_MAIN_SEARCH);
		manual_search.add(FunCDE.TRACE_ROUTES_SEARCH_PUBLIC);
	}

	private static void search_regist() {
		searchRegist.add(FunCDE.TRACE_COMMON_CLICK_SIGNUP);
	}

	private static void favorite_search() {
		favorite_search.add(FunCDE.TRACE_ROUTES_FAVORITE_ITEM);
		favorite_search.add(FunCDE.TRACE_MAIN_FAVORITE_ITEM);
	}

	private static void without_further_action() {
		without_further_action.add(FunCDE.TRACE_ROUTES_FOCUSROUTES);
		without_further_action.add(FunCDE.TRACE_ROUTES_FIRSTTHINGAFTERSEARCH);
	}

	private static void schedule_reliability() {
		schedule_reliability.add(FunCDE.TRACE_ROUTES_CLICKSSRRIMAGE);
		schedule_reliability.add(FunCDE.TRACE_ROUTES_SELECTSSRRPORTPAIR);
	}

	private static void refine_search() {
		
		refine_search.add(FunCDE.TRACE_ROUTES_SELECTCALENDAR);
		refine_search.add(FunCDE.TRACE_ROUTES_SELECTDIRECT);
		refine_search.add(FunCDE.TRACE_ROUTES_SELECTCYCUTOFFCALENDAR);
		refine_search.add(FunCDE.TRACE_ROUTES_SELECTARRIVALCALENDAR);
		refine_search.add(FunCDE.TRACE_ROUTES_SELECTDEPARTURECALENDAR);
		refine_search.add(FunCDE.TRACE_ROUTES_CHANGETRANSITTIME);
	}

	private static void show_map() {
		show_map.add(FunCDE.TRACE_ROUTES_SELECTDETAIL);
	}

}
