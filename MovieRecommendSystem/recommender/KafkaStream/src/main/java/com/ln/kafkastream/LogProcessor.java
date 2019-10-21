package com.ln.kafkastream;
/**
 * <p>
 * Project: MovieRecommendSystem
 * Package: com.ln.kafkastream
 * Version: V1.0.0
 * <p>
 * Created by LiNian on 2019/10/21 17:03
 */

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

/**
 * @ClassName LogProcessor
 * @AUTHOR LiNian
 * @DATE 2019/10/21 17:03
 * @Version: 1.0
 * @Description: TODO kafkaETL数据预处理
 */
public class LogProcessor implements Processor<byte[], byte[]> {

    private ProcessorContext context;

    @Override
    public void init(ProcessorContext processorContext) {
        this.context = processorContext;
    }

    @Override
    public void process(byte[] dummy, byte[] line) {
        // 把收集到的日志信息用string表示
        String input = new String(line);
        // 根据前缀MOVIE_RATING_PREFIX:从日志信息中提取评分数据
        if( input.contains("MOVIE_RATING_PREFIX:") ){
            System.out.println("movie rating data coming!>>>>>>>>>>>" + input);

            input = input.split("MOVIE_RATING_PREFIX:")[1].trim();
            context.forward( "logProcessor".getBytes(), input.getBytes() );
        }
    }

    @Override
    public void punctuate(long l) {

    }

    @Override
    public void close() {

    }
}
