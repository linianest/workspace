package com.ln.concurrency.chapter1;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter1
 * @version: 1.0
 */

/**
 * @ClassName:TemplateMethod
 * @Author:linianest
 * @CreateTime:2020/3/12 10:03
 * @version:1.0
 * @Description TODO: 模板方法:实现外部都一样，内部不一样的效果
 */
public class TemplateMethod {

    /**
     * 方法前面加上final关键字，代表这个方法不可以被子类的方法重写。
     * @param message
     */
    public final void print(String message) {
        System.out.println("############");
        wrapPrint(message);
        System.out.println("############");
    }

    protected void wrapPrint(String message) {

    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*" + message + "*");
            }
        };
        t1.print("Hello Thread");
        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("|" + message + "|");
            }
        };
        t2.print("Hello Thread");
    }

}
