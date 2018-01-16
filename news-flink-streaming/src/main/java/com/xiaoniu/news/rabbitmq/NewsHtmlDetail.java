package com.xiaoniu.news.rabbitmq;

import org.apache.flink.api.common.serialization.SerializationSchema;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hc on 2018/1/9.
 */
public class NewsHtmlDetail implements  Serializable{

    private String md5Id;
    private String url;

    public NewsHtmlDetail(String md5Id, String url) {
        this.md5Id = md5Id;
        this.url = url;
    }

    //    //新闻类型
//    private String newsType;
//    //新闻静态html
//    private String newsHtmlStr;

//    public String getNewsType() {
//        return newsType;
//    }
//
//    public String getNewsHtmlStr() {
//        return newsHtmlStr;
//    }
//
//    public NewsHtmlDetail(String newsType, String newsHtmlStr) {
//        this.newsType = newsType;
//        this.newsHtmlStr = newsHtmlStr;
//    }

    public NewsHtmlDetail() {
    }

    public String getMd5Id() {
        return md5Id;
    }

    public void setMd5Id(String md5Id) {
        this.md5Id = md5Id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public void setNewsType(String newsType) {
//        this.newsType = newsType;
//    }
//
//    public void setNewsHtmlStr(String newsHtmlStr) {
//        this.newsHtmlStr = newsHtmlStr;
//    }

//    @Override
//    public String toString() {
//        return new Date().toLocaleString() + "NewsHtmlDetail{" +
//                "newsType=" + newsType +
//                ", newsHtmlStr='" + newsHtmlStr + '\'' +
//                '}';
//    }

}
