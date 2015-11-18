package com.cargosmart.csmcs.report.common;

public enum JourneyType {
	REFINE_SEARCH("Search + refine search"),
	SHOW_MAP("Search + Show Map"),
	SCHEDULE_RELIABILITY("Search + Schedule Reliability"),
	SHOW1("Search + Show Map + Refine Search"),
	SHOW2("Search + Show Map + Schedule Reliability"),
	SHOW3("Search + Show Map + Refine Search + Schedule Reliability"),
	OTHERS("Search + Others"),
	SEARCH_END("Search (No further action)"), 
	SEARCH_REGISTER("Search + Registration (successful 'Register')");
	
	private String description;

	@Override
	public String toString() {
		return description;
	}
	
    JourneyType(String description) {
		this.description= description;
	}
}
