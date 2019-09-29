package com.ln.ct.common.bean;


import com.ln.ct.common.api.Column;
import com.ln.ct.common.api.Rowkey;
import com.ln.ct.common.api.TableRef;
import com.ln.ct.common.constant.Names;
import com.ln.ct.common.constant.ValueConstant;
import com.ln.ct.common.util.DateUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * @Description TODO
 * 基础的访问对象
 * @AUTHOR LiNian
 * @DATE 2019/9/26 13:47
 */
public abstract class BaseDao {

    private ThreadLocal<Connection> connHolder = new ThreadLocal<Connection>();
    private ThreadLocal<Admin> adminHolder = new ThreadLocal<Admin>();

    protected void start() throws Exception {
        getConnection();
        getAdmin();
    }

    protected void end() throws Exception {
        Admin admin = getAdmin();
        if (admin != null) {
            admin.close();
            adminHolder.remove();
        }
        Connection conn = getConnection();
        if (conn != null) {
            conn.close();
            connHolder.remove();
        }

    }


    /**
     * 创建命名空间，如果命名空间已经存在，不需要创建，否则创建新的
     */
    protected void createNamespaceNX(String namespace) throws Exception {
        Admin admin = getAdmin();
        try {
            admin.getNamespaceDescriptor(namespace);
        } catch (NamespaceNotFoundException e) {
//            e.printStackTrace();
            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespace).build();
            admin.createNamespace(namespaceDescriptor);
        }

    }

    /**
     * 创建表，如果表已经存在，则删除后创建新的
     *
     * @param name
     * @param families
     */
    protected void createTableXX(String name, String... families) throws Exception {
        createTableXX(name,null, null, families);
    }

    /**
     *
     * @param name
     * @param coprocessorClass 协处理器
     * @param regionCount
     * @param families
     * @throws Exception
     */
    protected void createTableXX(String name,String coprocessorClass, Integer regionCount, String... families) throws Exception {
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);
        if (admin.tableExists(tableName)) {
            // 表存在，删除表
            deleteTable(name);
        }
        // 创建表
        createTable(name,coprocessorClass, regionCount, families);
    }

    /**
     * 创建表
     *
     * @param name
     * @param families
     * @throws Exception
     */
    private void createTable(String name,String coprocessorClass, Integer regionCount, String... families) throws Exception {
        TableName tableName = TableName.valueOf(name);
        Admin admin = getAdmin();
        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
        if (families == null || families.length == 0) {
            families = new String[1];
            families[1] = Names.CF_INFO.getValue();
        }
        for (String family : families) {
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(family);
            tableDescriptor.addFamily(columnDescriptor);
        }

        if (coprocessorClass!=null&&!"".equals(coprocessorClass)){
            tableDescriptor.addCoprocessor(coprocessorClass);
        }


        // 增加预分区
        if (regionCount == null || regionCount <= 1) {
            admin.createTable(tableDescriptor);
        } else {
            // 分区键
            byte[][] splitKeys = genSplitKeys(regionCount);
            admin.createTable(tableDescriptor, splitKeys);
        }


    }



    /**
     * 获取查询时startrow,stoprow集合
     * @param tel
     * @param start
     * @param end
     * @return
     */
    protected static List<String[]> getStartStoreRowkeys(String tel ,String start,String end){
        ArrayList<String[]> rowkeyss = new ArrayList<>();


        String startTime = start.substring(0, 6);
        String endTime = end.substring(0, 6);
        Calendar startCal=Calendar.getInstance();
        startCal.setTime(DateUtil.parse(startTime,"yyyyMM"));

        Calendar endCal=Calendar.getInstance();
        endCal.setTime(DateUtil.parse(endTime,"yyyyMM"));

        while (startCal.getTimeInMillis()<=endCal.getTimeInMillis()){
            // 当前时间
            String nowTime=DateUtil.format(startCal.getTime(),"yyyyMM");
            int regionNum = genRegionNum(tel, nowTime);

            String startRow=regionNum+"_"+tel+"_"+nowTime;
            String stopRow=startRow+"|";
            String[] rowkeys={startRow,stopRow};
            rowkeyss.add(rowkeys);
            // 月份+1
            startCal.add(Calendar.MONTH,1);
        }

        return rowkeyss;

    }

    /**
     * 计算分区号
     *
     * @return
     */
    protected static int genRegionNum(String tel, String date) {

        // 13329482132
        String usercode = tel.substring(tel.length() - 4);
        // 20180402120000
        String yearMonth = date.substring(0, 6);
        int userCodeHash = usercode.hashCode();
        int yearMonthHash = yearMonth.hashCode();

        // crc 校验采用亦或算法
        int crc = Math.abs(userCodeHash ^ yearMonthHash);

        // 取模
        int regionCount = crc % ValueConstant.REGION_COUNT;
        return regionCount;
    }


    /**
     * 生成分区键
     *
     * @param regionCount
     * @return
     */
    private byte[][] genSplitKeys(int regionCount) {
        int splitKeyCount = regionCount - 1;
        byte[][] bs = new byte[splitKeyCount][];
        // 0|,1|,2|,3|,4|
        // 000111
        // 11000
        // 22111
        // (-∞,0|),[0|,1|),[1|,+∞)

        ArrayList<byte[]> bsList = new ArrayList<>();
        for (int i = 0; i < splitKeyCount; i++) {
            String splitKey = i + "|";
            bsList.add(Bytes.toBytes(splitKey));
        }
        Collections.sort(bsList,new Bytes.ByteArrayComparator());
        bsList.toArray(bs);
        return bs;
    }


    /**
     * 增加对象，自动封装对象数据，将对象数据保存到HBASE中
     *
     * @param obj
     */
    protected void putData(Object obj) throws Exception {
        // 反射
        Class clazz = obj.getClass();
        TableRef tableRef = (TableRef) clazz.getAnnotation(TableRef.class);
        String tableName = tableRef.value();
        String stringRowkey = "";
        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            Rowkey rowkey = f.getAnnotation(Rowkey.class);
            if (rowkey != null) {
                f.setAccessible(true);
                stringRowkey = (String) f.get(obj);
                break;
            }
        }
        // 获取表对象
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(stringRowkey));
        for (Field f : fs) {
            Column column = f.getAnnotation(Column.class);
            if (column != null) {
                String family = column.family();
                String colName = column.column();
                if (colName == null || "".equals(colName)) {
                    colName = f.getName();
                }
                f.setAccessible(true);
                String value = (String) f.get(obj);
                put.addColumn(Bytes.toBytes(family), Bytes.toBytes(colName), Bytes.toBytes(value));
            }
        }
        // 增加数据
        table.put(put);
        // 关闭表
        table.close();

    }

    /**
     * 增加多条数据
     *
     * @param name
     * @param puts
     */
    protected void putData(String name, List<Put> puts) throws Exception {
        // 获取表对象
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(name));

        // 增加数据
        table.put(puts);
        // 关闭表
        table.close();
    }
    /**
     * 增加数据
     *
     * @param name
     * @param put
     */
    protected void putData(String name, Put put) throws Exception {
        // 获取表对象
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(name));

        // 增加数据
        table.put(put);
        // 关闭表
        table.close();
    }


    /**
     * 删除表
     *
     * @param name
     * @throws Exception
     */
    protected void deleteTable(String name) throws Exception {
        TableName tableName = TableName.valueOf(name);
        Admin admin = getAdmin();
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }

    /**
     * 获取数据连接对象
     *
     * @return
     */
    protected synchronized Admin getAdmin() throws Exception {
        Admin admin = adminHolder.get();
        if (admin == null) {
            admin = getConnection().getAdmin();
            adminHolder.set(admin);
        }
        return admin;

    }

    /**
     * 获取数据连接对象
     *
     * @return
     */
    protected synchronized Connection getConnection() throws Exception {
        Connection conn = connHolder.get();
        if (conn == null) {
            Configuration conf = HBaseConfiguration.create();
            conn = ConnectionFactory.createConnection(conf);
            connHolder.set(conn);
        }
        return conn;

    }

}
