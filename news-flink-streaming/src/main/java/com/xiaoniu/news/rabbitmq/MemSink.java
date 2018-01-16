package com.xiaoniu.news.rabbitmq;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;

/**
 * Created by hc on 2018/1/9.
 */
public class MemSink<T> implements SinkFunction<T> {

    /**
     * 没过来一条数据调用一次
     */
    @Override
    public void invoke(T value) throws Exception {
        System.out.println("MemSink:   " + value);
    }

}