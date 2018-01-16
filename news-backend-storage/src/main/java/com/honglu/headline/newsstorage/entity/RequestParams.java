package com.honglu.headline.newsstorage.entity;

import com.honglu.headline.newsstorage.utils.PropertiesUtil;
/**
 * 达观接口调用请求参数
 * */
public class RequestParams {
	
	public final static String appid = PropertiesUtil.get("daguang.appid");
	
	public final static String appname = PropertiesUtil.get("daguang.appname");
	
	private String reqURL;
	
	private String textid;
	
	private String title;
	
	private String text;

	
	public static String getAppid() {
		return appid;
	}

	public static String getAppname() {
		return appname;
	}

	public String getReqURL() {
		return reqURL;
	}

	public void setReqURL(String reqURL) {
		this.reqURL = reqURL;
	}

	public String getTextid() {
		return textid;
	}

	public void setTextid(String textid) {
		this.textid = textid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
