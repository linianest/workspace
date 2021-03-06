package com.ln.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * flume 拦截器 数据清洗
 *
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/2 21:23
 */
public class LogETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 1 获取数据
        byte[] body = event.getBody();
        String log = new String(body, Charset.forName("UTF-8"));

        // 2 校验 启动（json） 事件日志类型
        if (log.contains("start")) {
            // 校验启动日志
            if (LogUtils.valuateStart(log)) {
                return event;
            }
        } else {
            if (LogUtils.valuateEvent(log)) {
                return event;
            }
        }

        return null;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        ArrayList<Event> interceptors = new ArrayList<>();

        for (Event event : events) {
            Event intercept1 = intercept(event);

            if (intercept1 != null) {
                interceptors.add(intercept1);
            }
        }
        return interceptors;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {

        @Override
        public Interceptor build() {

            return new LogETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
