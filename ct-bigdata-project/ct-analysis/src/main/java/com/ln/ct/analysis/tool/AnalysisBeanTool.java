package com.ln.ct.analysis.tool;

import com.ln.ct.analysis.io.MySQLBeanOutPutFormat;
import com.ln.ct.analysis.io.MySQLTextOutPutFormat;
import com.ln.ct.analysis.kv.AnalysisKey;
import com.ln.ct.analysis.kv.AnalysisValue;
import com.ln.ct.analysis.mapper.AnalysisBeanMapper;
import com.ln.ct.analysis.mapper.AnalysisTextMapper;
import com.ln.ct.analysis.reducer.AnalysisBeanReducer;
import com.ln.ct.analysis.reducer.AnalysisTextReducer;
import com.ln.ct.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

/**
 * @Description TODO 分析数据的工具类
 * @AUTHOR LiNian
 * @DATE 2019/9/27 18:07
 */
public class AnalysisBeanTool implements Tool {
    @Override
    public int run(String[] strings) throws Exception {

        Job job = Job.getInstance();

        job.setJarByClass(AnalysisBeanTool.class);

        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes(Names.CF_CALLER.getValue()));

        // mapper

        TableMapReduceUtil.initTableMapperJob(
                Names.TABLE.getValue(),
                scan,
                AnalysisBeanMapper.class,
                AnalysisKey.class,
                Text.class,
                job
        );

        // reducer
        job.setReducerClass(AnalysisBeanReducer.class);
        job.setOutputKeyClass(AnalysisKey.class);
        job.setOutputValueClass(AnalysisValue.class);

        // outputformat
        job.setOutputFormatClass(MySQLBeanOutPutFormat.class);


        boolean flag = job.waitForCompletion(true);
        if (flag) {
            return JobStatus.State.SUCCEEDED.getValue();
        } else {
            return JobStatus.State.FAILED.getValue();
        }
    }

    @Override
    public void setConf(Configuration configuration) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}
