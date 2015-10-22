package com.cargosmart.csmcs.report.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the CLIENTUSAGEDATAS database table.
 * 
 */
@Entity
@Table(name="CLIENTUSAGEDATAS")
public class Clientusagedata implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="_id")
	private String id;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createTime;

	private String domain;

	private String func;

	@Column(name="requestInformation#requestCookies#FREEMIUMSS_TOKENID_COOKIE")
	private String requestInformation_requestCookies_FREEMIUMSS_TOKENID_COOKIE;

	@Column(name="requestInformation#requestHeaders#referer")
	private String requestInformation_requestHeaders_referer;

	@Column(name="requestInformation#requestHeaders#user@agent")
	private String requestInformation_requestHeaders_user_agent;

	@Column(name="requestInformation#requestIp")
	private String requestInformation_requestIp;

	@Column(name="requestInformation#requestQuery#lang")
	private String requestInformation_requestQuery_lang;

	@Column(name="requestInformation#requestQuery#message")
	private String requestInformation_requestQuery_message;

	@Column(name="requestInformation#requestQuery#referrer")
	private String requestInformation_requestQuery_referrer;

	@Column(name="requestInformation#requestSession#ID")
	private String requestInformation_requestSession_ID;

	private String source;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updateTime;

	@Column(name="userIdentification#userID")
	private String userIdentification_userID;

	@Column(name="userIdentification#userType")
	private String userIdentification_userType;

	public Clientusagedata() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getFunc() {
		return this.func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getRequestInformation_requestCookies_FREEMIUMSS_TOKENID_COOKIE() {
		return this.requestInformation_requestCookies_FREEMIUMSS_TOKENID_COOKIE;
	}

	public void setRequestInformation_requestCookies_FREEMIUMSS_TOKENID_COOKIE(String requestInformation_requestCookies_FREEMIUMSS_TOKENID_COOKIE) {
		this.requestInformation_requestCookies_FREEMIUMSS_TOKENID_COOKIE = requestInformation_requestCookies_FREEMIUMSS_TOKENID_COOKIE;
	}

	public String getRequestInformation_requestHeaders_referer() {
		return this.requestInformation_requestHeaders_referer;
	}

	public void setRequestInformation_requestHeaders_referer(String requestInformation_requestHeaders_referer) {
		this.requestInformation_requestHeaders_referer = requestInformation_requestHeaders_referer;
	}

	public String getRequestInformation_requestHeaders_user_agent() {
		return this.requestInformation_requestHeaders_user_agent;
	}

	public void setRequestInformation_requestHeaders_user_agent(String requestInformation_requestHeaders_user_agent) {
		this.requestInformation_requestHeaders_user_agent = requestInformation_requestHeaders_user_agent;
	}

	public String getRequestInformation_requestIp() {
		return this.requestInformation_requestIp;
	}

	public void setRequestInformation_requestIp(String requestInformation_requestIp) {
		this.requestInformation_requestIp = requestInformation_requestIp;
	}

	public String getRequestInformation_requestQuery_lang() {
		return this.requestInformation_requestQuery_lang;
	}

	public void setRequestInformation_requestQuery_lang(String requestInformation_requestQuery_lang) {
		this.requestInformation_requestQuery_lang = requestInformation_requestQuery_lang;
	}

	public String getRequestInformation_requestQuery_message() {
		return this.requestInformation_requestQuery_message;
	}

	public void setRequestInformation_requestQuery_message(String requestInformation_requestQuery_message) {
		this.requestInformation_requestQuery_message = requestInformation_requestQuery_message;
	}

	public String getRequestInformation_requestQuery_referrer() {
		return this.requestInformation_requestQuery_referrer;
	}

	public void setRequestInformation_requestQuery_referrer(String requestInformation_requestQuery_referrer) {
		this.requestInformation_requestQuery_referrer = requestInformation_requestQuery_referrer;
	}

	public String getRequestInformation_requestSession_ID() {
		return this.requestInformation_requestSession_ID;
	}

	public void setRequestInformation_requestSession_ID(String requestInformation_requestSession_ID) {
		this.requestInformation_requestSession_ID = requestInformation_requestSession_ID;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserIdentification_userID() {
		return this.userIdentification_userID;
	}

	public void setUserIdentification_userID(String userIdentification_userID) {
		this.userIdentification_userID = userIdentification_userID;
	}

	public String getUserIdentification_userType() {
		return this.userIdentification_userType;
	}

	public void setUserIdentification_userType(String userIdentification_userType) {
		this.userIdentification_userType = userIdentification_userType;
	}

}