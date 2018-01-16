package com.honglu.headline.newssource.entity;

import java.io.Serializable;

/**
 * Created by hc on 2018/1/11.
 */
public class MiniImg implements Serializable{

//    "imgh": 240,
//            "imgn": "03200403.jpg",
//            "imgw": 320,
//            "src": "http://04.imgmini.eastday.com/mobile/20180109/03200403.jpg"
    private int imgh;
    private String imgn;
    private String imgw;
    private String src;

    public int getImgh() {
        return imgh;
    }

    public void setImgh(int imgh) {
        this.imgh = imgh;
    }

    public String getImgn() {
        return imgn;
    }

    public void setImgn(String imgn) {
        this.imgn = imgn;
    }

    public String getImgw() {
        return imgw;
    }

    public void setImgw(String imgw) {
        this.imgw = imgw;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public MiniImg() {}

    public MiniImg(String imgn, String src) {
        this.imgn = imgn;
        this.src = src;
    }
}
