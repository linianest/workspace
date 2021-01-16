package com.ln.concurrency.chapter1;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter1
 * @version: 1.0
 */

/**
 * @ClassName:TryConcurrency
 * @Author:linianest
 * @CreateTime:2020/3/11 22:34
 * @version:1.0
 * @Description TODO: 并发编程
 */
public class TryConcurrency {

    public static void main(String[] args) {
        readFromDataBase();
        writeDataToFile();
    }

    private static void readFromDataBase() {
        // read data from database and handle it.
        try {
            println("Begin read data from db.");
            Thread.sleep(1000 * 2L);
            println("read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }

    private static void writeDataToFile(){
        try {
            println("Begin write data to file.");
            Thread.sleep(1000 * 2L);
            println("write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }

    private static void println(String message){
        System.out.println(message);
    }


}
