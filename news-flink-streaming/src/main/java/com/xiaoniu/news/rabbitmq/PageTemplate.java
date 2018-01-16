package com.xiaoniu.news.rabbitmq;

import freemarker.template.Template;
import net.sf.json.JSONObject;

/**
 * Created by hc on 2018/1/10.
 */
public interface PageTemplate {
    String process(JSONObject newsJson);
}
