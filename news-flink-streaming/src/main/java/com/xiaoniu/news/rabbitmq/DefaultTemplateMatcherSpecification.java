package com.xiaoniu.news.rabbitmq;

import net.sf.json.JSONObject;

/**
 * 图文混排模板
 * Created by hc on 2018/1/10.
 */
public class DefaultTemplateMatcherSpecification implements TemplateMatcherSpecification{
    /**
     * 模板id
     */
    private PageTemplateEnum pageTemplateEnum;

    public DefaultTemplateMatcherSpecification(PageTemplateEnum pageTemplateEnum) {
        System.out.println("=========DefaultTemplateMatcherSpecification init============");
        this.pageTemplateEnum = pageTemplateEnum;
    }

    /**
     * 目前全部走这个模板
     * @param pageJsonObject
     * @return
     */
    @Override
    public boolean isMatched(JSONObject pageJsonObject) {
        return true;
    }

    @Override
    public PageTemplate getPageTemplate() {
        return pageTemplateEnum.getTemplate();
    }
}
