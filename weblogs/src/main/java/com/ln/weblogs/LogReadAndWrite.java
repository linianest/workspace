package com.ln.weblogs;

import javafx.scene.input.InputMethodRequests;

import java.io.*;

/**
 * @Description 
 * @AUTHOR LiNian
 * @DATE 2019/10/2 0:46
 */
public class LogReadAndWrite {

    private static String readFileName;
    private static String writeFileName;
    private static BufferedReader reader=null;
    private static PrintWriter writer=null;	


    public static void main(String[] args) {
        if(args.length<2){
            System.out.println("系统参数不正确，请按照指定格式传递：java -jar xxx.jar path1 path2");
            System.exit(1);
        }
        readFileName=args[0];
        writeFileName=args[1];
        readFileByLines(readFileName);
//         readFileByLines("D:\\Download\\weblogs.log");
    }

    public static void readFileByLines(String path) {
        try {
            reader=new BufferedReader(new InputStreamReader(new FileInputStream(path), "gbk"));
            String tempString=null;
            int count=0;
            while ((tempString=reader.readLine())!=null){
                Thread.sleep(300);
//                tempString=new String(tempString.getBytes("gbk"),"GBK");
                System.out.println("row:"+count+">>>"+tempString);
                method1(writeFileName,tempString);
                count++;
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void method1(String file,String content){
        try {
			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
            writer.write(content+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (writer!=null){
                writer.close();
            }
        }
    }
}
