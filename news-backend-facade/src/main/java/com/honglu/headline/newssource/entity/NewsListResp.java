package com.honglu.headline.newssource.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hc on 2018/1/11.
 */
public class NewsListResp implements Serializable{

//     {"date": "2018-01-10 13:56",
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

    private String date;
    private String type;
    private String rowkey;
    private String source;
    private String topic;
    private String url;
    List<MiniImg> miniimg;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public List<MiniImg> getMiniimg() {
        return miniimg;
    }

    public void setMiniimg(List<MiniImg> miniimg) {
        this.miniimg = miniimg;
    }

    public NewsListResp(String date, String type, String rowkey, String source, String topic, String url, List<MiniImg> miniimg) {
        this.date = date;
        this.type = type;
        this.rowkey = rowkey;
        this.source = source;
        this.topic = topic;
        this.url = url;
        this.miniimg = miniimg;
    }
}


