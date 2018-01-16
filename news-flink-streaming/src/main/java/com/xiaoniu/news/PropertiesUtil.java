package com.xiaoniu.news;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hc on 2018/1/10.
 */
public final class PropertiesUtil {
    private static Properties prop;
    static {
        prop = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/application.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getKey(String key){
        if(prop.getProperty(key)!=null){
            return prop.getProperty(key).trim();
        }
        return "";
    }

}
