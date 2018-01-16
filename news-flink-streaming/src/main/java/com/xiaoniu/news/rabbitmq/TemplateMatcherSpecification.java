package com.xiaoniu.news.rabbitmq;

import net.sf.json.JSONObject;

/**
 * Created by menghaoji on 2018/1/10.
 * 页面和模板匹配接口
 */
public interface TemplateMatcherSpecification {
    /**
     * 是否符合
     * @param pageJsonObject
     * @return
     */
    public boolean isMatched(JSONObject pageJsonObject);

    /**
     * 符合的话返回匹配的模板编号
     * @return
     */
    public PageTemplate getPageTemplate();
}
