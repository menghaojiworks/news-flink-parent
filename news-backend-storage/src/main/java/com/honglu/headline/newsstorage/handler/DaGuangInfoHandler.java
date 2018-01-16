package com.honglu.headline.newsstorage.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.honglu.headline.newsstorage.entity.RequestParams;
import com.honglu.headline.newsstorage.utils.HttpUtil;
import com.honglu.headline.newsstorage.utils.PropertiesUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

@JobHandler(value="DaGuangInfoHandler")
@Component
public class DaGuangInfoHandler extends IJobHandler{
	private Logger logger = LoggerFactory.getLogger(DaGuangInfoHandler.class);

	@Override
	public ReturnT<String> execute(String param) throws Exception {
		logger.info("***********达观信息处理任务开始***********");
		
		//从数据库爬虫原始表获取达观数据
		
		//调用达观标签接口
		RequestParams requestParams = new RequestParams();
		requestParams.setText("");
		
		
		
		
		requestParams.setTextid("");
		requestParams.setTitle("");
		requestParams.setReqURL(PropertiesUtil.get("daguang.tagURL"));
		
		String tagRes = HttpUtil.doRequest(requestParams);
		String tags = "";
		if(!tagRes.equalsIgnoreCase("")){
			tags = parseTagRes(tagRes);
		}else{
			logger.info("***********调用达观标签信息接口异常，没有获取到标签信息***********");
		}
		
		
		
		//调用达观分类接口
		requestParams.setReqURL(PropertiesUtil.get("daguang.classifyURL"));
		String classifyRes = HttpUtil.doRequest(requestParams);
		String classify = "";
		if(!classifyRes.equalsIgnoreCase("")){
			classify = parseClassifyRes(classifyRes);
		}else{
			logger.info("***********调用达观分类信息接口异常，没有获取到分类信息***********");
		}
		
		//将数据入数据库news_complete_info
		
		
		return null;
	}
	
	/**
	 * 调用达观标签接口
	 * */
	public String parseTagRes(String result){
		JSONObject obj = JSON.parseObject(result);
		String tagstatus = obj.getString("status"); // OK WARN FAIL
		StringBuffer sBuffer = new StringBuffer();
		String tags = "";
		
//		String requestId = obj.getString("request_id");
		
		if(!"FAIL".equalsIgnoreCase(tagstatus)){
			JSONArray taglist = obj.getJSONArray("tag_list");
			for(int i=0;i<taglist.size();i++){
				JSONObject tagObj = (JSONObject) taglist.get(i);
				String tag = tagObj.getString("tag");
				sBuffer.append(tag).append(",");
			}
			tags = sBuffer.toString();
			
		}else if("FAIL".equalsIgnoreCase(tagstatus)){
			JSONObject Obj2 = obj.getJSONObject("errors");
			int errcode = Obj2.getIntValue("code");
			String errorMessage = Obj2.getString("message");
			
			logger.info("没有获取到标签信息,errcode={},errorMessage={}"+errcode,errorMessage);
		}
		
		return tags;
	}
	
	/**
	 * 调用达观分类接口
	 * */
	public String parseClassifyRes(String result){
		JSONObject classifyObj = JSON.parseObject(result);
		String classifystatus = classifyObj.getString("status"); // OK WARN FAIL
		String classify = "";
		if(!"FAIL".equalsIgnoreCase(classifystatus)){
			JSONArray resArray = classifyObj.getJSONArray("result");
			JSONArray classifyArray = resArray.getJSONArray(0);
			classify = classifyArray.getString(0);
		}else if("FAIL".equalsIgnoreCase(classifystatus)){
			JSONObject Obj2 = classifyObj.getJSONObject("errors");
			int errcode = Obj2.getIntValue("code");
			String errorMessage = Obj2.getString("message");
			
			logger.info("没有获取到分类信息,errcode={},errorMessage={}"+errcode,errorMessage);
		}
		return classify;
	}
}
