package com.honglu.headline.newssource.entity;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

/**
 * Created by hc on 2018/1/11.
 */
public class NewsColumnResp implements Serializable{
//    {
//        "type": "yule",
//            "name": "娱乐",
//            "isup": "1"
//    },
    private String type;
    private String name;
    private String isup;

    public NewsColumnResp(String type, String name, String isup) {
        this.type = type;
        this.name = name;
        this.isup = isup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsup() {
        return isup;
    }

    public void setIsup(String isup) {
        this.isup = isup;
    }
}
