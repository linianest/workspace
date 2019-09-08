package com.ln.flume.interceptor;


import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类型拦截器区分数据
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/2 19:34
 */
public class LogTypeInterceptor implements Interceptor{
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {

        // 获取body
        byte[] body = event.getBody();
        String log = new String(body, Charset.forName("UTF-8"));

        // 获取header
        Map<String, String> headers = event.getHeaders();

        // 向header 中添加值
        if(log.contains("start")){
            headers.put("topic","topic_start");
        }else{
            headers.put("topic","topic_event");
        }

        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {

        ArrayList<Event> interceptors = new ArrayList<>();
        for (Event event: events) {

            Event intercept1=intercept(event);
            interceptors.add(intercept1);
        }


        return interceptors;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogTypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
