package com.ln.ct.common.util;

import java.text.DecimalFormat;

/**
 * @Description TODO 数字工具类
 * @AUTHOR LiNian
 * @DATE 2019/9/22 14:57
 */
public class NumberUtil {


    /**
     * 将数字格式为字符串
     * @param number
     * @param length
     * @return
     */
    public static String format(int number,int length) {
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i <length ; i++) {
            sb.append(0);
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        return df.format(number);
    }
}
