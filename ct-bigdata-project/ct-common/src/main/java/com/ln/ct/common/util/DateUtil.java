package com.ln.ct.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description TODO 日期工具类
 * @AUTHOR LiNian
 * @DATE 2019/9/22 15:06
 */
public class DateUtil {


    /**
     * 将日期按照指定的格式，格式成字符串
     * @param date
     * @return
     */
    public static String format(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将日期字符串按照指定的格式解析为日期对象
     *
     * @param dataString
     * @param format
     * @return
     */
    public static Date parse(String dataString, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date=null;
        try {
            date = sdf.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
