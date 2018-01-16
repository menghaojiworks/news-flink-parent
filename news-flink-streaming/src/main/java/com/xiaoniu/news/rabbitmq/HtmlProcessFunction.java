package com.xiaoniu.news.rabbitmq;

import net.sf.json.JSONObject;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hc on 2018/1/11.
 */
public class HtmlProcessFunction<I extends Tuple, O> extends ProcessFunction {

    List<TemplateMatcherSpecification> specifications;

    public HtmlProcessFunction(List<TemplateMatcherSpecification> specifications) {
        this.specifications = specifications;
    }

    public List<TemplateMatcherSpecification> getSpecifications() {
        return specifications;
    }

    @Override
    public void processElement(Object o, Context context, Collector collector) throws Exception {
               Tuple2<String, JSONObject> newsTuple2 = (Tuple2<String, JSONObject>) o;
               JSONObject newsJson = newsTuple2.getField(1);
//                String summary = newsJson.getJSONObject("comSpiderModel").getString("summary");
                String htmlBody = null;

                if (specifications != null && !specifications.isEmpty()) {
                    System.out.println("");
                    for (TemplateMatcherSpecification specification : specifications) {
                        if (specification.isMatched(newsJson)) {
                            htmlBody = specification.getPageTemplate().process(newsJson);
                            break;
                        }
                    }
                }
                if (htmlBody != null) {
                    collector.collect(new NewsHtmlDetail(newsTuple2.getField(0), htmlBody));
                }
    }
}
