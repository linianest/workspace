package com.ln.concurrency.chapter3;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter3
 * @version: 1.0
 */

/**
 *@ClassName:CreateThread3
 *@Author:linianest
 *@CreateTime:2020/3/14 11:32
 *@version:1.0
 *@Description TODO: 将数据压进栈
 */
public class CreateThread3 {

    private static int i =0;
    private static int counter=0;
    private byte[] bytes = new byte[1024];

    // JVM will create a thread named main.
    public static void main(String[] args) {
        // create a JVM stack

        // 栈操作，压栈
        try {
            add(0);
        } catch (Error e) {
            e.printStackTrace();
            System.out.println(counter);
        }
    }
    public static void add(int i){
        ++counter;
        add(i+1);
    }
}
//java.lang.StackOverflowError
//26401