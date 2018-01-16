package com.honglu.headline.newssource.dubbo;

import com.honglu.headline.newssource.entity.NewsColumnResp;
import com.honglu.headline.newssource.entity.NewsListReq;
import com.honglu.headline.newssource.entity.NewsListResp;

import java.util.List;

/**
 * Created by hc on 2018/1/11.
 */
public interface NewsSourceService {
    public List<NewsColumnResp> getNewsColumns();
    public List<NewsListResp> getNewsListByColumn(NewsListReq newsListReq);
}
