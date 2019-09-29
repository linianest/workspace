package com.ln.ct.consumer;

import com.ln.ct.common.bean.Consumer;
import com.ln.ct.consumer.bean.CalllogConsumer;

import java.io.IOException;

/**
 * @Description TODO
 * 启动消费者
 * 使用kafka的消费者获取flume采集的数据
 * 数据存储到Hbase中去
 * @AUTHOR LiNian
 * @DATE 2019/9/22 18:14
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {

        // 创建消费者
        Consumer consumer = new CalllogConsumer();
        //　消费数据
        consumer.consume();
        // 关闭资源
        consumer.close();

    }
}
