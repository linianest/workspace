package com.ln.flume.interceptor;

import org.apache.commons.lang.math.NumberUtils;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/2 21:31
 */
public class LogUtils {
    public static boolean valuateStart(String log) {

        // json
        if (log == null) {
            return false;
        }

        //　json 格式 {}
        if (!log.trim().startsWith("{") || !log.trim().endsWith("}")) {
            return false;
        }

        return true;
    }

    public static boolean valuateEvent(String log) {

        if (log == null) {
            return false;
        }

        // 時間|json{}

        // 1. 切割
        String[] logContents = log.split("\\|");

        // 2 校验内容json
        if (logContents.length != 2) {
            return false;
        }

        //　3 判断时间
        if (logContents[0].length() != 13 || !NumberUtils.isDigits(logContents[0])) {
            return false;
        }

        // 4 判断json
        if (!logContents[1].trim().startsWith("{") || !logContents[1].trim().endsWith("}")) {
            return false;
        }
        return true;
    }
}
