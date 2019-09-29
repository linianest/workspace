import com.ln.ct.common.bean.Data;
import com.ln.ct.common.bean.DataIn;
import com.ln.ct.common.bean.DataOut;
import com.ln.ct.common.util.DateUtil;
import com.ln.ct.common.util.JDBCUtil;
import com.ln.ct.producer.bean.Calllog;
import com.ln.ct.producer.bean.Contact;
import com.ln.ct.producer.bean.LocalFileProducer;
import com.ln.ct.producer.io.LocalFileDataIn;
import com.ln.ct.producer.io.LocalFileDataOut;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/22 13:20
 */
public class test {
    public static void main(String[] args) throws IOException {

       test2();


    }

    private static final Connection connection;

    static {
        // 获取资源
        connection = JDBCUtil.getConnection();
    }

    public static void test2() throws IOException {


     LocalFileDataIn ld=new LocalFileDataIn("E:\\workspace\\ct-bigdata-project\\doc\\contact.log");
     List<Contact> contacts = ld.read(Contact.class);
     for (Contact contact:contacts){
         inserUser(contact);
     }
        System.out.println(111);
    }


    public static void test1() {
        try {

            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtil.parse("20180101", "yyyyMMdd"));
            for (int i = 1; i <= 12; i++) {
                cal.set(Calendar.MONTH, i);
                insertMonth(2018,i);
                int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                for (int j = 1; j <= days; j++) {
                     insertDay(2018,i,j);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void inserUser(Contact contact) {
        String insertSQL = "insert into ct_user (tel,name) values(?,?)";
        PreparedStatement prestat = null;
        try {
            prestat = connection.prepareStatement(insertSQL);
            prestat.setString(1, contact.getTel());
            prestat.setString(2, contact.getName());
            prestat.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (prestat != null) {
                try {
                    prestat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void insertDay(int year,int month,int day) {
        String insertSQL = "insert into ct_date (year,month,day) values(?,?,?)";
        PreparedStatement prestat = null;
        try {
            prestat = connection.prepareStatement(insertSQL);
            prestat.setInt(1, year);
            prestat.setInt(2, month);
            prestat.setInt(3, day);
            prestat.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (prestat != null) {
                try {
                    prestat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void insertMonth(int year,int month) {
        String insertSQL = "insert into ct_date (year,month) values(?,?)";
        PreparedStatement prestat = null;
        try {
            prestat = connection.prepareStatement(insertSQL);
            prestat.setInt(1, year);
            prestat.setInt(2, month);
            prestat.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (prestat != null) {
                try {
                    prestat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static String getRandomPhonNumber() {
        StringBuffer sb = new StringBuffer();
        sb.append(1);
        Random random = new Random();
        for (int j = 0; j < 10; j++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static String getRandomPhoneName() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            sb.append(getRandomChar());
        }
        return sb.toString();
    }

    /**
     * 产生随机的汉字
     *
     * @return
     */
    private static char getRandomChar() {
        String str = "";
        int hightPos;
        int lowPos;
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos).byteValue());
        b[1] = (Integer.valueOf(lowPos).byteValue());
        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }

        return str.charAt(0);
    }
}
