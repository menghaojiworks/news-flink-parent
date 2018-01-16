package com.honglu.headline.newsstorage.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.honglu.headline.newsstorage.Mapper.NewsCompleteInfoRowMapper;
import com.honglu.headline.newsstorage.entity.NewsCompleteInfo;
import com.honglu.headline.newsstorage.utils.RedisUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

@JobHandler(value="NewsInfoHandler")
@Component
public class NewsInfoHandler extends IJobHandler{

	private Logger logger = LoggerFactory.getLogger(NewsInfoHandler.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private RedisUtils redisUtils;
	
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		
		logger.info("***********获取新闻信息任务开始***********");
		
	    List<NewsCompleteInfo> updateNewsList = new ArrayList<NewsCompleteInfo>();
		
		
		//第一次取数sql
		String selFirstSql = "select news_id,news_label,news_title,news_tag,news_tag_first,"
				+ "news_url,news_brief_url,pub_time,news_create_time,text_souce,app_type from news_complete_info where status=? and news_create_time is NOT NULL order by news_create_time desc";
		
		String selSql = "select news_id,news_label,news_title,news_tag,news_tag_first,"
				+ "news_url,news_brief_url,pub_time,news_create_time,text_souce,app_type from news_complete_info where news_create_time>? and status=? order by news_create_time desc";
		
		String LastCatchTime = redisUtils.get("LastCatchTime");
		if(LastCatchTime==null || "".equalsIgnoreCase(LastCatchTime)){
			updateNewsList = jdbcTemplate.query(selFirstSql, new Object[]{1}, new NewsCompleteInfoRowMapper());
		}else{
			updateNewsList = jdbcTemplate.query(selSql, new Object[]{new Date(Long.parseLong(LastCatchTime)),1}, new NewsCompleteInfoRowMapper());
		}
		
		if(updateNewsList.size()>0){
			NewsCompleteInfo newsCompleteInfo = updateNewsList.get(0);
			Long time = newsCompleteInfo.getCreatTime().getTime();
			redisUtils.set("LastCatchTime", String.valueOf(time));
			
			import2Redis(updateNewsList);
		}

		logger.info("***********获取新闻信息任务结束***********");
		return null;
	}

	
	public void import2Redis(List<NewsCompleteInfo> list){
		Map<String,List<NewsCompleteInfo>> updateNewsMap = list.stream().collect(Collectors.groupingBy(NewsCompleteInfo::getType));
		for(String key : updateNewsMap.keySet()){
			List<NewsCompleteInfo> updateNewsList = updateNewsMap.get(key);
			for(int j = updateNewsList.size() - 1; j >= 0; j--){
				String jsonStr = JSON.toJSONString(updateNewsList.get(j));
				redisUtils.lpush(key, jsonStr);
			}
			/*for(int i=0;i<updateNewsList.size();i++){
				String jsonStr = JSON.toJSONString(updateNewsList.get(i));
				redisUtils.lpush(key, jsonStr);
			}*/
		}
	}
}
