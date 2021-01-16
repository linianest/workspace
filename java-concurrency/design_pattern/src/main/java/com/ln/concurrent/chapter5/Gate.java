package com.ln.concurrent.chapter5;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter5
 * @version: 1.0
 */

/**
 * @ClassName:Gate
 * @Author:linianest
 * @CreateTime:2020/3/24 9:06
 * @version:1.0
 * @Description TODO: 定义共享资源
 */

/**
 * SharedResource
 */
public class Gate {
    private int counter = 0;
    private String name = "NoBody";
    private String address = "NoWhere";

    /**
     * 临界值
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.counter++;
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("************BROKEN**************" + toString());
        }
    }

    @Override
    public String toString() {
        return "NO." + counter + ":" + name + "," + address;
    }
}
