package com.cargosmart.csmcs.report.common;

public enum JourneyType {
	REFINE_SEARCH("refine search"),
	SHOW_MAP("show map"),
	SCHEDULE_RELIABILITY("schedule reliability"),
	SHOW1("show map with refine search"),
	SHOW2("show map with schedule reliability"),
	SHOW3("show map with refine search and schedule reliability"),
	OTHERS("others");
	
	private String description;

	@Override
	public String toString() {
		return description;
	}
	
    JourneyType(String description) {
		this.description= description;
	}
}
