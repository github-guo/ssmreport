package com.cargosmart.csmcs.report.common;

public enum JourneyType {
	REFINE_SEARCH("refine search"),
	SHOW_MAP("show map"),
	SCHEDULE_RELIABILITY("schedule reliability"),
	SHOW1("show map + refine search"),
	SHOW2("show map + schedule reliability"),
	SHOW3("show map + refine search + schedule reliability"),
	OTHERS("others"),
	SEARCH_END("search without further action"), 
	SEARCH_REGISTER("search + registration(successful 'Register')");
	
	private String description;

	@Override
	public String toString() {
		return description;
	}
	
    JourneyType(String description) {
		this.description= description;
	}
}
