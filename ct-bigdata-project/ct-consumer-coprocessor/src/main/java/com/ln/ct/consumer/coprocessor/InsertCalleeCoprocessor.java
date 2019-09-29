package com.ln.ct.consumer.coprocessor;

import com.ln.ct.common.bean.BaseDao;
import com.ln.ct.common.constant.Names;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @Description TODO 使用协处理器保存被叫用户的数据
 * 协处理器的使用
 * 1、创建类
 * 2、让表知道协处理类（和表有关联）
 * 3、将项目打成jar包发布到hbase的lib中（关联的jar包也需要发布），并且还要分发到集群中
 *
 * @AUTHOR LiNian
 * @DATE 2019/9/27 14:16
 */
public class InsertCalleeCoprocessor extends BaseRegionObserver {

    // 方法的命名规则

    /**
     * 保存主叫用户数据之后，由Hbase自动保存被叫用户数据
     *
     * @param e
     * @param put
     * @param edit
     * @param durability
     * @throws IOException
     */
    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put,
                        WALEdit edit, Durability durability) throws IOException {
        // 获取表
        Table table = e.getEnvironment().getTable(TableName.valueOf(Names.TABLE.getValue()));

        // 主叫用户的rowkey
        String callerRowkey = Bytes.toString(put.getRow());
        // 1_133_201803_144_1010_1
        String[] values = callerRowkey.split("_");

        ConprocessorDao dao = new ConprocessorDao();
        String call1 = values[1];
        String call2 = values[3];
        String calltime = values[2];
        String duration = values[4];
        String flag = values[5];
        if ("1".equals(flag)) {
            // 只有主叫用户保存后才会触发被叫用户的保存
            String calleeRowkey = dao.getRegionNum(call2, calltime) + "_" +
                    call2 + "_" + calltime + "_"+call1 +"_"+ duration + "_0";
            // 被叫用户
            Put calleePut = new Put(Bytes.toBytes(calleeRowkey));
            byte[] calleefamily = Bytes.toBytes(Names.CF_CALLEE.getValue());
            calleePut.addColumn(calleefamily, Bytes.toBytes("call1"), Bytes.toBytes(call2));
            calleePut.addColumn(calleefamily, Bytes.toBytes("call2"), Bytes.toBytes(call1));
            calleePut.addColumn(calleefamily, Bytes.toBytes("calltime"), Bytes.toBytes(calltime));
            calleePut.addColumn(calleefamily, Bytes.toBytes("duration"), Bytes.toBytes(duration));
            calleePut.addColumn(calleefamily, Bytes.toBytes("flag"), Bytes.toBytes("0"));

            // 保存数据
            table.put(calleePut);
            // 关闭表
            table.close();
        }


    }

    private class ConprocessorDao extends BaseDao {

        public int getRegionNum(String tel, String time) {
            return genRegionNum(tel, time);
        }

    }
}
