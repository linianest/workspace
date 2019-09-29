package com.ln.ct.analysis.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Description TODO 分析数据Reducer
 * @AUTHOR LiNian
 * @DATE 2019/9/27 18:39
 */
public class AnalysisTextReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        int sumCall = 0;
        int sumDuration = 0;
        for (Text value : values) {
            int duration = Integer.parseInt(value.toString());
            sumDuration += duration;
            sumCall++;
        }
        context.write(key, new Text(sumCall + "_" + sumDuration));
    }
}
