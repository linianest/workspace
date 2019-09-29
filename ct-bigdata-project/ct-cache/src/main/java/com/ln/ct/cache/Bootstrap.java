package com.ln.ct.cache;

import com.ln.ct.common.util.JDBCUtil;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description TODO 启动缓存客户端,向redis中缓存数据
 * @AUTHOR LiNian
 * @DATE 2019/9/28 17:02
 */
public class Bootstrap {
    public static void main(String[] args) {


        // 读取Mysql中的数据
        Map<String, Integer> userMap = new HashMap<>();
        Map<String, Integer> dateMap = new HashMap<>();

        Connection connection = null;
        PreparedStatement prestat = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtil.getConnection();
            // 读取用户，时间数据
            String queryUserSql = "select id,tel from ct_user";
            prestat = connection.prepareStatement(queryUserSql);
            rs = prestat.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String tel = rs.getString(2);
                userMap.put(tel, id);
            }
            rs.close();
            String queryDateSql = "select id,year,month,day from ct_date";
            prestat = connection.prepareStatement(queryDateSql);
            rs = prestat.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String year = rs.getString(2);
                String month = rs.getString(3);
                if (month==null||"".equals(month)){
                    month="";
                }else{
                    if (month.length()==1){
                        month = "0" + month;
                    }
                }

                String day = rs.getString(4);
                if (day==null||"".equals(day)){
                    day="";
                }else{
                    if (day.length()==1){
                        day = "0" + day;
                    }
                }

                dateMap.put(year+month+day, id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (prestat != null) {
                try {
                    prestat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
//            System.out.println(userMap.size());
//            System.out.println(dateMap.size());
        }

        // 向redis中存储数据
        Jedis jedis=new Jedis("mini1",6379);

        Iterator<String> iterator = userMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Integer value = userMap.get(key);
            jedis.hset("ct_user",key,value.toString());
        }
        iterator = dateMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Integer value = dateMap.get(key);
            jedis.hset("ct_date",key,value.toString());
        }


    }
}
