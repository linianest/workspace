package com.ln.concurrent.chapter7;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter7
 * @version: 1.0
 */

/**
 * @ClassName:ImmutablePerformance
 * @Author:linianest
 * @CreateTime:2020/3/25 13:36
 * @version:1.0
 * @Description TODO: 不可变对象与可变对象加锁的性能对比
 */
public class ImmutablePerformance {
    public static void main(String[] args) {
        // sync 51520
        long startTimestamp = System.currentTimeMillis();
//        SyncObj syncObj = new SyncObj();
//        syncObj.setName("Alex");

        // immutable 29729
        ImmutableObj immutableObj = new ImmutableObj("Alex");
        for (long i = 0L; i <1_000_000 ; i++) {
            System.out.println(immutableObj.toString());
        }
        long endTimestamp = System.currentTimeMillis();
        System.out.println("Elasped time "+(endTimestamp-startTimestamp));
    }
}

/**
 * 定义不可变对象
 */
final class ImmutableObj {
    private final String name;

    public ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}

// 可变对象
class SyncObj {
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "[" + name + "]";
    }
}
