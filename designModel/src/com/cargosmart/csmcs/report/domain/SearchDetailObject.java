package com.cargosmart.csmcs.report.domain;

import java.util.Date;

public class SearchDetailObject {
	
	private String searchJourney;
	private String customerType;
	private String customerSegment;
	private String searchMethod;
	private String searchPlatform;
	private String userId;
	private String ip;
	private Date searchDate;
	private String id;

	public String getSearchJourney() {
		return searchJourney;
	}

	public void setSearchJourney(String searchJourney) {
		this.searchJourney = searchJourney;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerSegment() {
		return customerSegment;
	}

	public void setCustomerSegment(String customerSegment) {
		this.customerSegment = customerSegment;
	}

	public String getSearchMethod() {
		return searchMethod;
	}

	public void setSearchMethod(String searchMethod) {
		this.searchMethod = searchMethod;
	}

	public String getSearchPlatform() {
		return searchPlatform;
	}

	public void setSearchPlatform(String searchPlatform) {
		this.searchPlatform = searchPlatform;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	public void setId(String id) {
		this.id=id;
	}
	
	public String getId(){
		return this.id;
	}

}
