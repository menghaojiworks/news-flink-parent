package com.xiaoniu.news.rabbitmq;

import net.sf.json.JSONObject;
import org.apache.flink.api.common.serialization.SerializationSchema;

/**
 * Created by hc on 2018/1/15.
 */
public class NewsHtmlDetailSchema implements SerializationSchema<NewsHtmlDetail> {

    @Override
    public byte[] serialize(NewsHtmlDetail newsHtmlDetail) {

        if (null != newsHtmlDetail) {

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> NewsHtmlDetailSchema serialize() >>>>>>>>>>>>>>>>>>>>>>");
            String jsonObject = JSONObject.fromObject(newsHtmlDetail).toString();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> [jsonObject]=" + jsonObject);

            return jsonObject.getBytes();
        }

        return new byte[0];
    }
}
