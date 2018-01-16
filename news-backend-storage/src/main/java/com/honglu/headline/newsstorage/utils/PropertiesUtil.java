package com.honglu.headline.newsstorage.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;


/**
 * 读取config.properties配置
 * 
 * @author gdl
 *
 */
public class PropertiesUtil {

	private static Properties properties = new Properties();
	static {
		InputStream in = PropertiesUtil.class.getResourceAsStream("/config.properties");
		try {
			properties.load(in);
		} catch (IOException e) {

		}
	}

	public static String get(String key) {
		if(StringUtils.isBlank(properties.getProperty(key))){
			return "";
		}
		return properties.getProperty(key).trim();
	}
}
