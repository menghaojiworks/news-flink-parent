package com.xiaoniu.news.rabbitmq;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hc on 2018/1/10.
 */
public class DefaultPageTemplate implements PageTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPageTemplate.class);

    private String templatePath;

    private Template template;

    /**
     * @param templatePath
     */
    public DefaultPageTemplate(String templatePath) {
        this.templatePath = templatePath;
        readTemplate();
    }

    /**
     * 这里根据templatePath 去读取template
     */
    private void readTemplate() {
        LOGGER.error(">>>>>> read template");
        Configuration cfg = new Configuration(new Version("2.3.23"));
        try {
            cfg.setClassLoaderForTemplateLoading(DefaultPageTemplate.class.getClassLoader(), "template");
            template = cfg.getTemplate(templatePath);
        } catch (IOException e) {
            LOGGER.error("========" + e);
            throw new RuntimeException(e);
        }

    }


    @Override
    public String process(JSONObject newsJson) {

        LOGGER.info(">>>>>> process[{}]", newsJson);

        String summary = newsJson.getString("summary");
        LOGGER.info(">>>>>> [summary]=" + summary);
        String tag = newsJson.getString("tag");
        LOGGER.info(">>>>>> [tag]=" + tag);
        String title = newsJson.getString("title");
        LOGGER.info(">>>>>> [title]=" + title);
        String url = newsJson.getString("url");
        LOGGER.info(">>>>>> [url]=" + url);
        String urlLevel = newsJson.getString("url_level");
        LOGGER.info(">>>>>> [url_level]=" + urlLevel);

        JSONArray jsonArray = newsJson.getJSONArray("coreImgsText");
        LOGGER.info(">>>>>> [coreImgsText]=" + jsonArray);

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;
        JSONObject jsonObject = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            map = new HashMap<>();
            map.put("content", jsonObject.getString("content"));
            map.put("type", jsonObject.getString("type"));
            list.add(map);
        }

        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("author", "xiaobian");// FIXME 待定字段
        objectMap.put("from", "中国网");// FIXME 待定字段
        objectMap.put("keywords", summary);
        objectMap.put("description", summary);
        objectMap.put("creatTime", "2018-01-10 11:13");// FIXME 待定字段
        objectMap.put("dataset", list);
        objectMap.put("title", title);

        Writer out = new StringWriter();
        try {
            template.process(objectMap, out);
        } catch (Exception e) {
            LOGGER.info(">>>>>> [Exception]=" + e);
            return null;
        }

        LOGGER.info(">>>>>> [html]=" + out.toString());

        return out.toString();
    }


    // FIXME 仅供测试，需删除
    public static void main(String[] args) {


        // FIXME 模拟从RabbitMQ获取的数据
//        String jsonString = "{\"code\": \"1000\",\"coreImgsText\": [{\"content\": \"https://08.imgmini.eastday.com/mobile/20180110/20180110111344_39d02636632c627db2a3fd7b66115b9a_1.jpeg\",\"type\": \"image\"},{\"content\": \"中国网娱乐1月10日讯 掐指一算，最大一批的90后都即将突破三十大关。他们中的很多已经结婚生子，迈上生活的正轨。婚姻、子女、处理事业与家庭的种种问题，是每个年轻人走向成熟的必经之路，所以“育儿”成为很多年轻父母关注的新热点，本周《嗨！看电视》素人嘉宾就观看了真人版“育儿教科书”——《萌仔萌萌宅》，他们从中窥见了自身的影子，触发了更深层次的共鸣。\",\"type\": \"text\"},{\"content\": \"https://08.imgmini.eastday.com/mobile/20180110/20180110111344_e3f246568aed799fdeafb147fc0104fe_2.jpeg\",\"type\": \"image\"},{\"content\": \"面对四个性格迥异的萌娃，《嗨！看电视》嘉宾们变身“迷弟”“迷妹”，全程遮掩不住微笑。新婚小夫妻宋昱欣和邓梓杭因为孩子的性别问题讨论激烈，邓梓杭与大多数女人一样想生个女儿，把孩子打扮成童话里的小公主，但她却对女儿未来极有可能长得像爸爸这件事上非常介意，邓梓杭对此却不是很认同。二毛爸爸则延续了直白幽默的一贯风格，萌娃“肯尼”登场时，用塑料味极浓的普通话大声表白：“哦呦，我喜欢这个(孩子)！\",\"type\": \"text\"},{\"content\": \"https://08.imgmini.eastday.com/mobile/20180110/20180110111344_70beb832ce80865ace5e5b471ebb0059_3.jpeg\",\"type\": \"image\"},{\"content\": \"二胎时代已经开启，《萌仔萌萌宅》暴露了年轻父母育儿的窘境，揭露“中国式家庭”在育儿问题上的真实面貌，初为父母的张歆艺、袁弘夫妻虽然手忙脚乱，但责任心很强，面对萌娃的突发状况，张歆艺不假思索直接用手接住呕吐物，嘉宾们对此好评一致。在孩子的教育问题上，嘉宾们也有话要说，二毛一家认为现在对幼儿教育太过用力，爸爸更是声称自己只有“胎教毕业”水平，逗得二毛哈哈大笑。“中西文化交流大使”——“美国女婿”铁蛋和岳母爆发争执，揭露中美教育的巨大差异，中国和美国的教育理念会有哪些不同呢？铁蛋又是怎么化解和岳母的尴尬争论的？本周的《嗨！看电视》将会告诉你答案。\",\"type\": \"text\"},{\"content\": \"第二期《嗨！看电视》带领观众感受了育儿的酸甜苦辣，糗事百出笑掉大牙。独乐乐不如众乐乐，让我们和“人体弹幕”们一起嗨起来吧！周四晚22：00，记得锁定湖南卫视《嗨！看电视》，我们和你不见不散。\",\"type\": \"text\"},{\"content\": \"面对四个性格迥异的萌娃，《嗨！看电视》嘉宾们变身“迷弟”“迷妹”，全程遮掩不住微笑。新婚小夫妻宋昱欣和邓梓杭因为孩子的性别问题讨论激烈，邓梓杭与大多数女人一样想生个女儿，把孩子打扮成童话里的小公主，但她却对女儿未来极有可能长得像爸爸这件事上非常介意，邓梓杭对此却不是很认同。二毛爸爸则延续了直白幽默的一贯风格，萌娃“肯尼”登场时，用塑料味极浓的普通话大声表白：“哦呦，我喜欢这个(孩子)！\",\"type\": \"text\"}],\"summary\": \"东方头条,头条新闻,头条,今日新闻头条,头条网,头条新闻,今日头条新闻\",\"tag\": \"娱乐\",\"title\": \"照顾萌童遭遇窘境 《嗨！看电视》为张歆艺夫妇点赞\",\"url\": \"http://mini.eastday.com/a/171220011653694.html\",\"url_level\": \"1\",\"url_md5\": \"009810e00cf4be54824bd54951052a76\"}";

        String jsonString ="{\"code\": \"0_eastday_18648\",\"coreImgsText\": [{\"content\": \"http://spider--test-oss.oss-cn-shanghai.aliyuncs.com/east_day/2018-01-11/307b9f15-563e-4a91-9476-c43ad229d3f4.jpeg\",\"type\": \"image\"}, {\"content\": \" 义胆扶危豪气强，\",\"type\": \"text\"}, {\"content\": \" 忠肝护法志高昂。\",\"type\": \"text\"}, {\"content\": \" 燃烧自己驱魔鬼，\",\"type\": \"text\"}, {\"content\": \" 泽被他人照佛光。\",\"type\": \"text\"}, {\"content\": \" (张文岭)\",\"type\": \"text\"}, {\"content\": \" 好人事迹：赵玉富是一名中共党员，已经年近六旬，作为涡阳县建筑公司下岗职工，虽然生活异常困难，他却用一颗正直的心，一直坚持用自己的力量驱除邪恶，伸张正义。\",\"type\": \"text\"}],\"summary\": \"[母亲发现儿子不对劲, anglebaby曝出儿子正面照, 网友]\",\"tag\": \"娱乐\",\"title\": \"【好诗赞好人】好人姓名：赵玉富_新闻频道_东方头条\",\"url\": \"http://mini.eastday.com/a/171220092049981.html\",\"url_level\": \"1\"}";
        JSONObject jsonObject = JSONObject.fromObject(jsonString);

        new DefaultPageTemplate("default_news_template.ftl").process(jsonObject);
    }


}
