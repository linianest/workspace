package com.ln.udf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.json.JSONObject;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/7 23:10
 */
public class BaseFieldUDF extends UDF {

    public String evaluate(String line, String jsonkeysString) {
        StringBuilder sb = new StringBuilder();

        // 1、获取所有的key mid uv ......
        String[] jsonkeys = jsonkeysString.split(",");

        // 2、line 服务器时间|json
        String[] logContents = line.split("\\|");

        // 3 校验
        if (logContents.length != 2 || StringUtils.isBlank(logContents[1])) {
            return "";
        }


        try {
            // 4 对logContents[1]创建json对象
            JSONObject jsonObject = new JSONObject(logContents[1]);

            // 5 获取公共字段的json对象
            JSONObject cmJson = jsonObject.getJSONObject("cm");

            // 6 循环便利
            for (int i = 0; i < jsonkeys.length; i++) {
                String jsonkey = jsonkeys[i].trim();

                if (cmJson.has(jsonkey)) {
                    sb.append(cmJson.getString(jsonkey)).append("\t");
                } else {
                    sb.append("\t");
                }
            }

            // 7 拼接事件字段和服务器时间
            sb.append(jsonObject.getString("et")).append("\t");
            sb.append(logContents[0]).append("\t");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String line="1567861965283|{\"cm\":{\"ln\":\"-56.8\",\"sv\":\"v2.9.0\",\"os\":\"8.2.7\",\"g\":\"T9G669VW@gmail.com\",\"mid\":\"12\",\"nw\":\"WIFI\",\"l\":\"pt\",\"vc\":\"4\",\"hw\":\"1080*1920\",\"ar\":\"MX\",\"uid\":\"12\",\"t\":\"1567793879153\",\"la\":\"-24.4\",\"md\":\"Sumsung-0\",\"vn\":\"1.0.3\",\"ba\":\"Sumsung\",\"sr\":\"E\"},\"ap\":\"app\",\"et\":[{\"ett\":\"1567787228383\",\"en\":\"loading\",\"kv\":{\"extend2\":\"\",\"loading_time\":\"10\",\"action\":\"2\",\"extend1\":\"\",\"type\":\"1\",\"type1\":\"433\",\"loading_way\":\"2\"}},{\"ett\":\"1567767415478\",\"en\":\"notification\",\"kv\":{\"ap_time\":\"1567808101077\",\"action\":\"1\",\"type\":\"1\",\"content\":\"\"}},{\"ett\":\"1567814665818\",\"en\":\"notification\",\"kv\":{\"access\":\"\",\"push_id\":\"2\"}},{\"ett\":\"1567786730021\",\"en\":\"notification\",\"kv\":{\"active_source\":\"3\"}},{\"ett\":\"1567778001365\",\"en\":\"comment\",\"kv\":{\"p_comment_id\":2,\"addtime\":\"1567784854199\",\"praise_count\":435,\"replay_count\":1136,\"other_id\":9,\"comment_id\":9,\"userid\":5,\"content\":\"能硒咽蚕址兰错瘟取涟瞩址负瘁甫定隶蝶饮酮固主责\"}},{\"ett\":\"1567797139693\",\"en\":\"praise\",\"kv\":{\"target_id\":0,\"id\":7,\"type\":4,\"add_time\":\"1567861394322\",\"userid\":5}}]}";
        String x= new BaseFieldUDF().evaluate(line,"mid,uid,vc,vn,l,sr,os,ar,md,ba,sv,g,hw,t,nw,ln,la,t");
        System.out.println(x);
    }

}
