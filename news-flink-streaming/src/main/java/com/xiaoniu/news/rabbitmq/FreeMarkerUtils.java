package com.xiaoniu.news.rabbitmq;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerUtils {

    static Configuration configuration = null;


    static {

        configuration = new Configuration(new Version("2.3.23"));

        try {
            configuration.setDirectoryForTemplateLoading(new File("/html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, TemplateException {


        Template template = configuration.getTemplate("content.ftl");

        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("author", "Cleam Lee");
        dataModel.put("keywords", "东方头条,头条新闻,头条,今日新闻头条,头条网,头条新闻,今日头条新闻");
        dataModel.put("description", "东方头条网-东方网旗下《东方头条》是一款会自动学习的资讯软件,它会分析你的兴趣爱好,为你推荐喜欢的内容,并且越用越懂你.就要你好看,东方头条新闻网!");
        dataModel.put("title", "照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞");
        dataModel.put("article_title", "照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞");
        dataModel.put("creat_time", "2018-01-10 11:13");
        dataModel.put("article_from", "来源：中国网");
        dataModel.put("article_image_min", "https://08.imgmini.eastday.com/mobile/20180110/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg");
        dataModel.put("article_image_max", "https://08.imgmini.eastday.com/mobile/20180110/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg");
        dataModel.put("article_content", "中国网娱乐1月10日讯\n" +
                "            掐指一算，最大一批的90后都即将突破三十大关。他们中的很多已经结婚生子，迈上生活的正轨。婚姻、子女、处理事业与家庭的种种问题，是每个年轻人走向成熟的必经之路，所以“育儿”成为很多年轻父母关注的新热点，本周《嗨！看电视》素人嘉宾就观看了真人版“育儿教科书”——《萌仔萌萌宅》，他们从中窥见了自身的影子，触发了更深层次的共鸣。");

        Writer out = new OutputStreamWriter(new FileOutputStream("content.html"));

        template.process(dataModel, out);

    }

}
