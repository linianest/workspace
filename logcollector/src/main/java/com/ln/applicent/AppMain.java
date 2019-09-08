package com.ln.applicent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ln.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 日志行为模拟
 *
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/1 17:45
 */
public class AppMain {

    private final static Logger logger = LoggerFactory.getLogger(AppMain.class);
    private static Random rand = new Random();

    private static int s_mid = 0;
    private static int s_uid = 0;
    // 商品id
    private static int s_goodsid = 0;

    public static void main(String[] args) {
        // 控制每条发送的延时时间，默认是0
        Long delay = args.length > 0 ? Long.parseLong(args[0]) : 0L;
        // 循环遍历的次数
        int loop_len = args.length > 1 ? Integer.parseInt(args[1]) : 1000;
        // 生成数据
        generateLog(delay, loop_len);
    }

    private static void generateLog(Long delay, int loop_len) {
        for (int i = 0; i < loop_len; i++) {
            int flag = rand.nextInt(2);
            switch (flag) {
                case (0):
                    AppStart appStart = generateStart();
                    String jsonString = JSON.toJSONString(appStart);

                    // 控制台打印
                    logger.info(jsonString);
                    break;
                case (1):
                    JSONObject json = new JSONObject();

                    json.put("ap", "app");
                    json.put("cm", generateComFields());
                    JSONArray eventsArray = new JSONArray();

                    // 事件日志
                    // 商品点击，展示
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateDisplay());
                        json.put("et", eventsArray);

                    }
                    // 商品详情页
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateNewsDetail());
                        json.put("et", eventsArray);
                    }
                    // 商品列表页
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateNewList());
                        json.put("et", eventsArray);
                    }
                    // 广告
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateAd());
                        json.put("et", eventsArray);
                    }
                    // 消息通知
                    if (rand.nextBoolean()) {
                        eventsArray.add((generateNotifaction()));
                        json.put("et", eventsArray);
                    }
                    // 用户前台活跃
                    if (rand.nextBoolean()) {
                        eventsArray.add((generatbeforeground()));
                        json.put("et", eventsArray);
                    }
                    // 用户后台活跃
                    if (rand.nextBoolean()) {
                        eventsArray.add((generateBackground()));
                        json.put("et", eventsArray);
                    }
                    // 故障日志
                    if (rand.nextBoolean()) {
                        eventsArray.add((generateError()));
                        json.put("et", eventsArray);
                    }
                    // 用户评论
                    if (rand.nextBoolean()) {
                        eventsArray.add((generateComment()));
                        json.put("et", eventsArray);
                    }
                    // 用户收藏
                    if (rand.nextBoolean()) {
                        eventsArray.add((generateFavorites()));
                        json.put("et", eventsArray);
                    }
                    // 用户点赞
                    if (rand.nextBoolean()) {
                        eventsArray.add((generatePraise()));
                        json.put("et", eventsArray);
                    }

                    long millis = System.currentTimeMillis();
                    // 控制台打印
                    logger.info(millis + "|" + json.toJSONString());
                    break;
            }

            // 延时
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 后台活跃
     *
     * @return
     */
    private static JSONObject generateBackground() {

        AppActive_background appActive_background = new AppActive_background();


        // 1=push 2 icon 3 其他
        int flag = rand.nextInt(3) + 1;
        appActive_background.setActive_source(flag + "");

        JSONObject evenJson = (JSONObject) JSON.toJSON(appActive_background);

        return packEventJson("active_background", evenJson);


    }

    /**
     * 错误日志
     *
     * @return
     */
    private static JSONObject generateError() {

        AppErrorLog appErrorLog = new AppErrorLog();

        String[] errorBriefs = {"at cn.lift.dfdf.web.validateioness23", "at cn.lift.dfdf.web.getskdisnww"};

        String[] errorDetailds = {"java.lang.ArithmeticException", "java.lang.ArithmeticException"};

        appErrorLog.setErrorBrief(errorBriefs[rand.nextInt(errorBriefs.length)]);
        appErrorLog.setErrorDetail(errorDetailds[rand.nextInt(errorDetailds.length)]);

        JSONObject evenJson = (JSONObject) JSON.toJSON(appErrorLog);

        return packEventJson("error", evenJson);

    }

    /**
     * 收藏
     *
     * @return
     */
    private static JSONObject generateFavorites() {
        AppFavorites favorites = new AppFavorites();

        favorites.setCourse_id(rand.nextInt(10));
        favorites.setUserid(rand.nextInt(10));

        favorites.setAdd_time((System.currentTimeMillis() - rand.nextInt(99999999) + ""));

        JSONObject evenJson = (JSONObject) JSON.toJSON(favorites);

        return packEventJson("favorites", evenJson);


    }

    /**
     * 评论
     *
     * @return
     */
    private static JSONObject generateComment() {
        AppComment appComment = new AppComment();

        appComment.setComment_id(rand.nextInt(10));
        appComment.setUserid(rand.nextInt(10));
        appComment.setP_comment_id(rand.nextInt(5));

        appComment.setContent(getCONTENT());
        appComment.setAddtime((System.currentTimeMillis() - rand.nextInt(99999999)) + "");
        appComment.setOther_id(rand.nextInt(10));
        appComment.setPraise_count(rand.nextInt(1000));
        appComment.setReplay_count(rand.nextInt(2000));


        JSONObject evenJson = (JSONObject) JSON.toJSON(appComment);

        return packEventJson("comment", evenJson);

    }

    /**
     * 拼接多个汉字
     *
     * @return
     */
    private static String getCONTENT() {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < rand.nextInt(100); i++) {
            str.append(getRandomChar());
        }
        return str.toString();
    }

    /**
     * 前台活跃
     *
     * @return
     */
    private static JSONObject generatbeforeground() {
        AppActive_foreground appActive_foreground = new AppActive_foreground();

        int flag = rand.nextInt(2);
        switch (flag) {
            case 1:
                appActive_foreground.setAccess(flag + "");
                break;
            default:
                appActive_foreground.setAccess("");
                break;
        }

        // 1=push 2 icon 3 其他
        flag = rand.nextInt(3) + 1;
        appActive_foreground.setPush_id(flag + "");

        JSONObject evenJson = (JSONObject) JSON.toJSON(appActive_foreground);

        return packEventJson("active_forground", evenJson);


    }

    /**
     * 点赞
     *
     * @return
     */
    private static JSONObject generatePraise() {

        AppPraise appPraise = new AppPraise();

        appPraise.setId(rand.nextInt(10));
        appPraise.setUserid(rand.nextInt(10));
        appPraise.setTarget_id(rand.nextInt(10));
        appPraise.setType(rand.nextInt(4) + 1);
        appPraise.setAdd_time((System.currentTimeMillis() - rand.nextInt(999999)) + "");

        JSONObject evenJson = (JSONObject) JSON.toJSON(appPraise);

        return packEventJson("praise", evenJson);

    }

    /**
     * 广告相关字段
     *
     * @return
     */
    private static JSONObject generateAd() {

        AppAd appAd = new AppAd();

        int flag = rand.nextInt(3) + 1;
        appAd.setEntry(flag + "");

        flag = rand.nextInt(5) + 1;
        appAd.setAction(flag + "");

        // 失败吗
        flag = rand.nextInt(10);
        switch (flag) {
            case 1:
                appAd.setDetail("102");
                break;
            case 2:
                appAd.setDetail("201");
                break;
            case 3:
                appAd.setDetail("325");
                break;
            case 4:
                appAd.setDetail("433");
                break;
            case 5:
                appAd.setDetail("542");
                break;
            default:
                appAd.setDetail("");
                break;
        }

        flag = rand.nextInt(4) + 1;
        appAd.setSource(flag + "");

        flag = rand.nextInt(2) + 1;
        appAd.setBehavior(flag + "");

        flag = rand.nextInt(10);
        appAd.setNewstype(flag + "");

        flag = rand.nextInt(6);
        appAd.setShow_style(flag + "");

        JSONObject evenJson = (JSONObject) JSON.toJSON(appAd);

        return packEventJson("ad", evenJson);

    }

    /**
     * 商品列表
     *
     * @return
     */
    private static JSONObject generateNewList() {
        AppLoading appLoading = new AppLoading();

        int flag = rand.nextInt(3) + 1;
        appLoading.setAction(flag + "");
        flag = rand.nextInt(10) + rand.nextInt(7);
        appLoading.setLoading_time(flag + "");

        // 失败吗
        flag = rand.nextInt(10);
        switch (flag) {
            case 1:
                appLoading.setType1("102");
                break;
            case 2:
                appLoading.setType1("201");
                break;
            case 3:
                appLoading.setType1("325");
                break;
            case 4:
                appLoading.setType1("433");
                break;
            case 5:
                appLoading.setType1("542");
                break;
            default:
                appLoading.setType1("");
                break;

        }

        flag = rand.nextInt(2) + 1;
        appLoading.setLoading_way(flag + "");

        appLoading.setExtend1("");

        appLoading.setExtend2("");

        flag = rand.nextInt(3) + 1;
        appLoading.setType(flag + "");


        JSONObject evenJson = (JSONObject) JSON.toJSON(appLoading);

        return packEventJson("loading", evenJson);

    }

    /**
     * 商品详情
     *
     * @return
     */
    private static JSONObject generateNewsDetail() {

        AppNewsDetail appNewsDetail = new AppNewsDetail();

        int flag = rand.nextInt(3) + 1;
        appNewsDetail.setEntry(flag + "");

        appNewsDetail.setAction((rand.nextInt(4) + 1) + "");

        appNewsDetail.setGoodsId(s_goodsid + "");

        flag = rand.nextInt(3) + 1;
        appNewsDetail.setShowtype(flag + "");

        flag = rand.nextInt(6);
        appNewsDetail.setNews_staytime(flag + "");

        flag = rand.nextInt(10) + rand.nextInt(7);
        appNewsDetail.setLoading_time(flag + "");

        // 失败吗
        flag = rand.nextInt(10);
        switch (flag) {
            case 1:
                appNewsDetail.setType1("102");
                break;
            case 2:
                appNewsDetail.setType1("201");
                break;
            case 3:
                appNewsDetail.setType1("325");
                break;
            case 4:
                appNewsDetail.setType1("433");
                break;
            case 5:
                appNewsDetail.setType1("542");
                break;
            default:
                appNewsDetail.setType1("");
                break;

        }

        flag = rand.nextInt(100) + 1;
        appNewsDetail.setCategory(flag + "");

        JSONObject evenJson = (JSONObject) JSON.toJSON(appNewsDetail);

        return packEventJson("newsdetail", evenJson);


    }

    /**
     * 商品展示事件
     *
     * @return
     */
    private static JSONObject generateDisplay() {
        AppDisplay appDisplay = new AppDisplay();

        boolean boolFlag = rand.nextInt(10) < 7;

        // 动作 曝光商品=1 点击商品=2
        if (boolFlag) {
            appDisplay.setAction("1");
        } else {
            appDisplay.setAction("2");
        }

        String goodsId = s_goodsid + "";
        s_goodsid++;

        appDisplay.setGoodsId(goodsId);

        int flag = rand.nextInt(6);
        appDisplay.setPlace("" + flag);

        flag = rand.nextInt(2);
        appDisplay.setExtend1("" + flag);

        // 分类
        flag = rand.nextInt(100) + 1;
        appDisplay.setCategory("" + flag);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(appDisplay);
        return packEventJson("display", jsonObject);
    }

    /**
     * 为各个事件类型的公共字段
     *
     * @param eventName
     * @param jsonObject
     * @return
     */
    private static JSONObject packEventJson(String eventName, JSONObject jsonObject) {
        JSONObject eventJson = new JSONObject();
        eventJson.put("ett", (System.currentTimeMillis()) - rand.nextInt(99999999) + "");
        eventJson.put("en", eventName);
        eventJson.put("kv", jsonObject);
        return eventJson;
    }

    /**
     * 公共字段设置
     *
     * @return
     */
    private static JSONObject generateComFields() {
        AppBase appBase = new AppBase();
        appBase.setMid(s_mid + "");
        s_mid++;
        appBase.setUid(s_uid + "");
        s_uid++;
        appBase.setVc("" + rand.nextInt(20));
        appBase.setVn("1." + rand.nextInt(4) + "." + rand.nextInt(10));
        appBase.setOs("8." + rand.nextInt(3) + "." + rand.nextInt(10));

        //语言
        int flag = rand.nextInt(3);
        switch (flag) {
            case 0:
                appBase.setL("es");
                break;
            case 1:
                appBase.setL("en");
                break;
            case 2:
                appBase.setL("pt");
                break;
        }

        //渠道号
        appBase.setSr(getRandomChar(1));
        // 区域
        flag = rand.nextInt(2);
        switch (flag) {
            case 0:
                appBase.setAr("BR");
            case 1:
                appBase.setAr("MX");
        }
        // 手机品牌
        flag = rand.nextInt(3);
        switch (flag) {
            case 0:
                appBase.setBa("Sumsung");
                appBase.setMd("Sumsung-" + rand.nextInt(20));
                break;
            case 1:
                appBase.setBa("Huawei");
                appBase.setMd("Huawei-" + rand.nextInt(20));
                break;
            case 2:
                appBase.setBa("HTC");
                appBase.setMd("HTC-" + rand.nextInt(20));
                break;
        }
        // 嵌入版本号
        appBase.setSv("v2." + rand.nextInt(10) + "." + rand.nextInt(10));
        appBase.setG(getRandomCharAndNumr(8) + "@gmail.com");

        // 屏幕宽高
        flag = rand.nextInt(4);
        switch (flag) {
            case 0:
                appBase.setHw("640*940");
                break;
            case 1:
                appBase.setHw("640*1136");
                break;
            case 2:
                appBase.setHw("750*1134");
                break;
            case 3:
                appBase.setHw("1080*1920");
                break;
        }

        // 日志时间
        long millis = System.currentTimeMillis();
        appBase.setT("" + (millis - rand.nextInt(99999999)));

        // 手机网络模式
        flag = rand.nextInt(3);
        switch (flag) {
            case 0:
                appBase.setNw("3G");
                break;
            case 1:
                appBase.setNw("4G");
                break;
            case 2:
                appBase.setNw("WIFI");
                break;
        }

        // 经纬度
        appBase.setLn((-34 - rand.nextInt(83) - rand.nextInt(60) / 10.0) + "");
        appBase.setLa((32 - rand.nextInt(85) - rand.nextInt(60) / 10.0) + "");

        return (JSONObject) JSON.toJSON(appBase);
    }

    private static AppStart generateStart() {

        AppStart appStart = new AppStart();
        appStart.setMid(s_mid + "");
        s_mid++;
        appStart.setUid(s_uid + "");
        s_uid++;
        appStart.setVc("" + rand.nextInt(20));
        appStart.setVn("1." + rand.nextInt(4) + "." + rand.nextInt(10));
        appStart.setOs("8." + rand.nextInt(3) + "." + rand.nextInt(10));
        appStart.setEn("start");

        int flag = rand.nextInt(3);
        switch (flag) {
            case 0:
                appStart.setL("es");
                break;
            case 1:
                appStart.setL("en");
                break;
            case 2:
                appStart.setL("pt");
                break;
        }
        //渠道号
        appStart.setSr(getRandomChar(1));
        // 区域
        flag = rand.nextInt(2);
        switch (flag) {
            case 0:
                appStart.setAr("BR");
            case 1:
                appStart.setAr("MX");
        }

        // 手机品牌
        flag = rand.nextInt(3);
        switch (flag) {
            case 0:
                appStart.setBa("Sumsung");
                appStart.setMd("Sumsung-" + rand.nextInt(20));
                break;
            case 1:
                appStart.setBa("Huawei");
                appStart.setMd("Huawei-" + rand.nextInt(20));
                break;
            case 2:
                appStart.setBa("HTC");
                appStart.setMd("HTC-" + rand.nextInt(20));
                break;
        }

        // 嵌入版本号
        appStart.setSv("v2." + rand.nextInt(10) + "." + rand.nextInt(10));
        appStart.setG(getRandomCharAndNumr(8) + "@gmail.com");

        // 屏幕宽高
        flag = rand.nextInt(4);
        switch (flag) {
            case 0:
                appStart.setHw("640*940");
                break;
            case 1:
                appStart.setHw("640*1136");
                break;
            case 2:
                appStart.setHw("750*1134");
                break;
            case 3:
                appStart.setHw("1080*1920");
                break;
        }

        // 日志时间
        long millis = System.currentTimeMillis();
        appStart.setT("" + (millis - rand.nextInt(99999999)));

        // 手机网络模式
        flag = rand.nextInt(3);
        switch (flag) {
            case 0:
                appStart.setNw("3G");
                break;
            case 1:
                appStart.setNw("4G");
                break;
            case 2:
                appStart.setNw("WIFI");
                break;
        }

        // 经纬度
        appStart.setLn((-34 - rand.nextInt(83) - rand.nextInt(60) / 10.0) + "");
        appStart.setLa((32 - rand.nextInt(85) - rand.nextInt(60) / 10.0) + "");

        // 入口
        flag = rand.nextInt(5) + 1;
        appStart.setEntry(flag + "");

        // 开屏广告类型
        flag = rand.nextInt(2) + 1;
        appStart.setOpen_ad_type(flag + "");

        // 状态
        flag = rand.nextInt(10) > 8 ? 2 : 1;
        appStart.setAction(flag + "");

        //加载时长
        appStart.setLoading_time(rand.nextInt(20) + "");

        // 失败吗
        flag = rand.nextInt(10);
        switch (flag) {
            case 1:
                appStart.setDetail("102");
                break;
            case 2:
                appStart.setDetail("201");
                break;
            case 3:
                appStart.setDetail("325");
                break;
            case 4:
                appStart.setDetail("433");
                break;
            case 5:
                appStart.setDetail("542");
                break;
            default:
                appStart.setDetail("");
                break;

        }

        // 扩展字段
        appStart.setExtend1("");
        return appStart;
    }

    /**
     * 获取随机字母数字组合
     *
     * @param length
     * @return
     */
    private static String getRandomCharAndNumr(int length) {

        StringBuilder str = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            boolean b = random.nextBoolean();
            if (b) {
                str.append((char) (65 + random.nextInt(26)));
            } else {
                str.append(String.valueOf(random.nextInt(10)));
            }
        }
        return str.toString();
    }

    /**
     * 获取随机字母组合
     *
     * @param length
     * @return
     */
    private static String getRandomChar(int length) {

        StringBuilder str = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            str.append((char) (65 + random.nextInt(26)));
        }
        return str.toString();

    }


    /**
     * @return
     */
    private static char getRandomChar() {
        String str = "";
        int hightPos;
        int lowPos;
        Random random=new Random();
        hightPos=(176+Math.abs(random.nextInt(39)));
        lowPos=(161+Math.abs(random.nextInt(93)));

        byte[] b=new byte[2];
        b[0]=(Integer.valueOf(hightPos).byteValue());
        b[1]=(Integer.valueOf(lowPos).byteValue());

        try {
            str=new String(b,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }

        return str.charAt(0);

    }

    /**
     * 消息通知
     *
     * @return
     */
    private static JSONObject generateNotifaction() {
        AppNotification appNotification = new AppNotification();
        int flag = rand.nextInt(4) + 1;
        appNotification.setAction(flag + "");

        flag = rand.nextInt(4) + 1;
        appNotification.setType(flag + "");

        appNotification.setAp_time(System.currentTimeMillis() - rand.nextInt(99999999) + "");

        appNotification.setContent("");
        JSONObject evenJson = (JSONObject) JSON.toJSON(appNotification);

        return packEventJson("notification", evenJson);


    }

}

