package com.ln.ct.common.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/27 23:18
 */
public class JDBCUtil {

    private static final String MYSQL_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://mini1:3306/ct?useUnicode=true&characterEncoding=UTF-8";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "123456";

    public static Connection getConnection() {

        Connection conn = null;
        try {
            Class.forName(MYSQL_DRIVER_CLASS);
            conn = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
