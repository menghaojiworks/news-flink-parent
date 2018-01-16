package com.honglu.headline.newssource.entity;

import java.io.Serializable;

/**
 * Created by hc on 2018/1/11.
 */
public class NewsListReq implements Serializable{

//    参数名	必选	类型	说明
//    type	是	String	频道类型
//    pagenum	是	String	页码
//    idx	是	String	最新一条新闻的位置

    private String type;
    private String pagenum;
    private String idx;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPagenum() {
        return pagenum;
    }

    public void setPagenum(String pagenum) {
        this.pagenum = pagenum;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public NewsListReq() {
    }

    public NewsListReq(String type, String pagenum, String idx) {
        this.type = type;
        this.pagenum = pagenum;
        this.idx = idx;
    }
}
