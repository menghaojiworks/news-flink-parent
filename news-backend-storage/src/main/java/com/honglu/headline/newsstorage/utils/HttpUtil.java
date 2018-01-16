package com.honglu.headline.newsstorage.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.honglu.headline.newsstorage.entity.RequestParams;

public class HttpUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public static String doRequest(RequestParams params){
		
		String reqURL = params.getReqURL();
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("appid", RequestParams.appid);
		paramMap.put("title", params.getTitle());
		paramMap.put("textid", params.getTextid());
		paramMap.put("text", params.getText());
		
		String responseStr = "";
		try {
			responseStr = doPost(reqURL,paramMap);
		} catch (Exception e) {
			logger.error("http请求发生异常"+e);
			e.printStackTrace();
		} 
		
		return responseStr;
	}
	
	public static String doPost(String reqURL, Map<String, String> params) throws ParseException, IOException{
		String responseContent = "";

		HttpPost httpPost = new HttpPost(reqURL);
		if (params != null) {
		List nvps = new ArrayList();
		Set<Entry<String, String>> paramEntrys = params.entrySet();
		for (Entry<String, String> entry : paramEntrys) {
		nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		}

		httpPost.setHeader("User-Agent", "datagrand/datareport/java sdk v1.0.0");
		httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");

		HttpClient httpClient = new DefaultHttpClient();
		HttpParams httpParams = httpClient.getParams();
		HttpConnectionParams.setSoTimeout(httpParams, 60*1000);
		HttpConnectionParams.setConnectionTimeout(httpParams, 60*1000);

		HttpResponse response = httpClient.execute(httpPost);
		StatusLine status = response.getStatusLine();
		if (status.getStatusCode() >= HttpStatus.SC_MULTIPLE_CHOICES) {
			logger.info("Did not receive successful HTTP response: status code = {}, status message = {}",status.getStatusCode(), status.getReasonPhrase());
			httpPost.abort();
		}

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			responseContent = EntityUtils.toString(entity, "utf-8");
			EntityUtils.consume(entity);
		} else {
			logger.info("Http entity is null! request url is {},response status is {}", reqURL, response.getStatusLine());
		}
		return responseContent;
	}
}
