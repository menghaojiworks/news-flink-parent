package com.honglu.headline.newssource.dubbo.impl;

import com.honglu.headline.newssource.dubbo.NewsSourceService;
import com.honglu.headline.newssource.entity.MiniImg;
import com.honglu.headline.newssource.entity.NewsColumnResp;
import com.honglu.headline.newssource.entity.NewsListReq;
import com.honglu.headline.newssource.entity.NewsListResp;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hc on 2018/1/12.
 */
@Service(version = "1.0.0", retries = -1, timeout = 10000)
public class NewsSourceServiceImpl implements NewsSourceService {
    @Override
    public List<NewsColumnResp> getNewsColumns() {

        return Arrays.asList(
                new NewsColumnResp[]{
                    new NewsColumnResp("yule", "娱乐", "1"),
                        new NewsColumnResp("youxi", "游戏", "1"),
                        new NewsColumnResp("caijing", "财经", "1"),
                        new NewsColumnResp("shehui", "社会", "1"),
                        new NewsColumnResp("keji", "科技", "1"),
                        new NewsColumnResp("tiyu", "体育", "1"),
                        new NewsColumnResp("junshi", "军事", "1"),
                        new NewsColumnResp("xingzuo", "星座", "1")
                }
        );
    }

    @Override
    public List<NewsListResp> getNewsListByColumn(NewsListReq newsListReq) {

//        {"date": "2018-01-10 13:56",
//             "type": "yule",
//             "rowkey": "9223370521378057904",
//             "source": "中国网",
//             "topic": "照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞",
//             "url":"http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/news/news_sample.html",
//             "miniimg": [
//    {
//        "imgh": 240,
//            "imgn": "20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg",
//            "imgw": 320,
//            "src": "http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg"
//    }，
//{
//        "imgh": 240,
//            "imgn": "20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg",
//            "imgw": 320,
//            "src": "http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg"
//    }，
//{
//        "imgh": 240,
//            "imgn": "20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg",
//            "imgw": 320,
//            "src": "http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg"
//    }
//            ]
//}

        return Arrays.asList(new NewsListResp("2018-01-10 13:56","yule","9223370521378057904","中国网","照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/news/news_sample.html",Arrays.asList(new MiniImg("20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg"),new MiniImg("20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg"),new MiniImg("20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg"))),
                new NewsListResp("2018-01-10 13:56","yule","9223370521378057904","中国网","照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/news/news_sample.html",Arrays.asList(new MiniImg("20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg"),new MiniImg("20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg"),new MiniImg("20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg"))),
                new NewsListResp("2018-01-10 13:56","yule","9223370521378057904","中国网","照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/news/news_sample.html",Arrays.asList(new MiniImg("20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg"),new MiniImg("20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg"),new MiniImg("20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg"))),
                new NewsListResp("2018-01-10 13:56","yule","9223370521378057904","中国网","照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/news/news_sample.html",Arrays.asList(new MiniImg("20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg"),new MiniImg("20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg"),new MiniImg("20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg"))),
                new NewsListResp("2018-01-10 13:56","yule","9223370521378057904","中国网","照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/news/news_sample.html",Arrays.asList(new MiniImg("20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg"),new MiniImg("20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg"),new MiniImg("20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg"))),
                new NewsListResp("2018-01-10 13:56","yule","9223370521378057904","中国网","照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/news/news_sample.html",Arrays.asList(new MiniImg("20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg"),new MiniImg("20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg"),new MiniImg("20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg"))),
                new NewsListResp("2018-01-10 13:56","yule","9223370521378057904","中国网","照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/news/news_sample.html",Arrays.asList(new MiniImg("20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg"),new MiniImg("20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg"),new MiniImg("20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg"))),
                new NewsListResp("2018-01-10 13:56","yule","9223370521378057904","中国网","照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/news/news_sample.html",Arrays.asList(new MiniImg("20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg"),new MiniImg("20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg"),new MiniImg("20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg"))),
                new NewsListResp("2018-01-10 13:56","yule","9223370521378057904","中国网","照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/news/news_sample.html",Arrays.asList(new MiniImg("20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg"),new MiniImg("20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg"),new MiniImg("20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg"))),
                new NewsListResp("2018-01-10 13:56","yule","9223370521378057904","中国网","照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/news/news_sample.html",Arrays.asList(new MiniImg("20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg"),new MiniImg("20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg"),new MiniImg("20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg","http://news-source-oss.oss-cn-hangzhou.aliyuncs.com/imgs/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg"))));
    }
}
