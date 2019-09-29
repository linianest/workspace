package com.ln.ct.analysis.io;

import com.ln.ct.analysis.kv.AnalysisKey;
import com.ln.ct.analysis.kv.AnalysisValue;
import com.ln.ct.common.util.JDBCUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description TODO MySQL数据格式化输出对象
 * @AUTHOR LiNian
 * @DATE 2019/9/27 18:43
 */
public class MySQLBeanOutPutFormat extends OutputFormat<AnalysisKey, AnalysisValue> {

    protected static class MySQLRecordWriter extends RecordWriter<AnalysisKey, AnalysisValue> {

        private Connection connection = null;
        private Jedis jedis=null;

        public MySQLRecordWriter() {
            // 获取资源
            connection = JDBCUtil.getConnection();
            jedis=new Jedis("mini1",6379);
        }

        /**
         * 输出数据
         */
        @Override
        public void write(AnalysisKey key, AnalysisValue value) throws IOException, InterruptedException {

            String sumcall=value.getSumCall();
            String sumduration=value.getSumDuration();
            String insertSQL = "insert into ct_call (telid,dateid,sumcall,sumduration) values(?,?,?,?)";
            PreparedStatement prestat=null;
            try {
                prestat = connection.prepareStatement(insertSQL);

                prestat.setInt(1, Integer.parseInt(jedis.hget("ct_user",key.getTel())));
                prestat.setInt(2,Integer.parseInt(jedis.hget("ct_date",key.getDate())));
                prestat.setInt(3,Integer.parseInt(sumcall));
                prestat.setInt(4,Integer.parseInt(sumduration));
                prestat.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if (prestat!=null){
                    try {
                        prestat.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        /**
         * 释放资源
         *
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (jedis!=null){
                jedis.close();
            }
        }
    }

    @Override
    public RecordWriter<AnalysisKey, AnalysisValue> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new MySQLRecordWriter();
    }

    @Override
    public void checkOutputSpecs(JobContext jobContext) throws IOException, InterruptedException {

    }


    private FileOutputCommitter committer = null;

    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get("mapreduce.output.fileoutputformat.outputdir");
        return name == null ? null : new Path(name);
    }

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if (this.committer == null) {
            Path output = getOutputPath(context);
            this.committer = new FileOutputCommitter(output, context);
        }

        return this.committer;
    }
}
