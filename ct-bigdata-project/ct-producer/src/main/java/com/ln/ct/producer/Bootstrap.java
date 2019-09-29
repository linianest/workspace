package com.ln.ct.producer;

import com.ln.ct.producer.bean.LocalFileProducer;
import com.ln.ct.producer.io.LocalFileDataIn;
import com.ln.ct.producer.io.LocalFileDataOut;

import java.io.IOException;

/**
 * @Description TODO 启动对象
 * @AUTHOR LiNian
 * @DATE 2019/9/22 13:04
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {

        if(args.length<2){
            System.out.println("系统参数不正确，请按照指定格式传递：java -jar Produce.jar path1 path2");
            System.exit(1);
        }

        // 构建生产者对象
        LocalFileProducer producer = new LocalFileProducer();

//        producer.setIn(new LocalFileDataIn("E:\\workspace\\ct-bigdata-project\\doc\\contact.log"));
//        producer.setOut(new LocalFileDataOut("E:\\workspace\\ct-bigdata-project\\doc\\call.log"));
        producer.setIn(new LocalFileDataIn(args[0]));
        producer.setOut(new LocalFileDataOut(args[1]));

        // 生产数据
        producer.produce();

        // 关闭生产者对象
        producer.close();

    }

}
