package com.ln.ct.analysis.mapper;

import com.ln.ct.analysis.kv.AnalysisKey;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * @Description TODO 分析数据mapper
 * @AUTHOR LiNian
 * @DATE 2019/9/27 18:23
 */
public class AnalysisBeanMapper extends TableMapper<AnalysisKey, Text> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {

        String rowkey = Bytes.toString(key.get());
        String[] values = rowkey.split("_");
        String call1 = values[1];
        String call2 = values[3];
        String calltime = values[2];
        String duration = values[4];

        String year = calltime.substring(0, 4);
        String month = calltime.substring(0, 6);
        String day = calltime.substring(0, 8);
        // 主叫用户-年
        context.write(new AnalysisKey(call1, year), new Text(duration));
        // 主叫用户-月
        context.write(new AnalysisKey(call1, month), new Text(duration));
        // 主叫用户-日
        context.write(new AnalysisKey(call1, day), new Text(duration));

        // 被叫用户-年
        context.write(new AnalysisKey(call2, year), new Text(duration));
        // 被叫用户-月
        context.write(new AnalysisKey(call2, month), new Text(duration));
        // 被叫用户-日
        context.write(new AnalysisKey(call2, day), new Text(duration));
    }
}
