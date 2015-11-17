package com.cargosmart.csmcs.report.entrance;

import java.util.List;

import com.cargosmart.csmcs.report.domain.Clientusagedata;
import com.cargosmart.csmcs.report.domain.SearchDetailObject;

public interface DataTools {
	public List<Clientusagedata> allSearch(String id);
	public void writeSearchDetails(List<SearchDetailObject> searchDetailList);
	public List<String> getRegistUserIDs();
	public List<String> getPublishUserIPs();
	public String getSearchSegment(String userID);
}
