package com.honglu.headline.newsstorage.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class NewsCompleteInfo {
	
	/**
	 * 新闻发布时间
	 * */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date date;
	
	/**
	 * 生成html时间
	 * */
	@JSONField(serialize=false)  
	private Date creatTime;
	
	/**
	 * 一级分类
	 * */
	private String type;
	
	/**
	 * 新闻ID
	 * */
	private String rowkey;
	
	/**
	 * 新闻来源
	 * */
	private String source;
	
	/**
	 * 标题
	 * */
	private String topic;
	
	/**
	 * 地址
	 * */
	private String url;
	
	/**
	 * 图片地址
	 * */
	private String miniimg;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRowkey() {
		return rowkey;
	}

	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMiniimg() {
		return miniimg;
	}

	public void setMiniimg(String miniimg) {
		this.miniimg = miniimg;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	
	
	
	
}
