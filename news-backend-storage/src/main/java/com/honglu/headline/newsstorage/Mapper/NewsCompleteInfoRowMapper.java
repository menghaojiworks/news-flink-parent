package com.honglu.headline.newsstorage.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.honglu.headline.newsstorage.entity.NewsCompleteInfo;

public class NewsCompleteInfoRowMapper implements RowMapper<NewsCompleteInfo>{

	@Override
	public NewsCompleteInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		NewsCompleteInfo newsCompleteInfo = new NewsCompleteInfo();
		newsCompleteInfo.setRowkey(rs.getString("news_id"));
		newsCompleteInfo.setDate(rs.getTimestamp("pub_time"));
		newsCompleteInfo.setTopic(rs.getString("news_title"));
		newsCompleteInfo.setSource(rs.getString("text_souce"));
		newsCompleteInfo.setType(rs.getString("news_tag"));
		newsCompleteInfo.setUrl(rs.getString("news_url"));
		newsCompleteInfo.setMiniimg(rs.getString("news_brief_url"));
		newsCompleteInfo.setCreatTime(rs.getTimestamp("news_create_time"));
		return newsCompleteInfo;
	}

}
