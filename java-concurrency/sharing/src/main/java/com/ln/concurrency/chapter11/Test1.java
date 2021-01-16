package com.ln.concurrency.chapter11;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter11
 * @version: 1.0
 */

/**
 *@ClassName:Test1
 *@Author:linianest
 *@CreateTime:2020/3/18 18:44
 *@version:1.0
 *@Description TODO:
 */
public class Test1 {

    private Test2 test2 = new Test2();

    public void test(){
        test2.test();
    }

}
