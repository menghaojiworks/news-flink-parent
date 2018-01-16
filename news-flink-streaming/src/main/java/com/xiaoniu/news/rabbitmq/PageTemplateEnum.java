package com.xiaoniu.news.rabbitmq;

import freemarker.template.Template;

/**
 * Created by hc on 2018/1/10.
 */
public enum PageTemplateEnum {

    DEFAULT_TEMPLATE(new Integer(1), new DefaultPageTemplate("default_news_template.ftl"));

    private Integer templateType;
    private PageTemplate template;

    PageTemplateEnum(Integer templateType, PageTemplate template) {
        this.templateType = templateType;
        this.template = template;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public PageTemplate getTemplate() {
        return template;
    }
}
