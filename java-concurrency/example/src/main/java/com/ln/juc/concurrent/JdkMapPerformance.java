package com.ln.juc.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.collections.concurrent
 * @Name:JdkMapPerformance
 * @Author:linianest
 * @CreateTime:2021/1/13 11:33
 * @version:1.0
 * @Description TODO: jdk压力测试
 */
public class JdkMapPerformance {
    /**
     * Concurrency Testing use five threads
     *
     * <pre>
     *      Start pressure testing the map [class java.util.Hashtable] use the threshold [5],retrieve=false
     *      2500000 element inserted/retrieved in 10826 ms
     *      2500000 element inserted/retrieved in 2380 ms
     *      2500000 element inserted/retrieved in 2136 ms
     *      2500000 element inserted/retrieved in 1706 ms
     *      2500000 element inserted/retrieved in 1628 ms
     *      For the map [class java.util.Hashtable] the average times is 3735 ms
     *      Start pressure testing the map [class java.util.Hashtable] use the threshold [5],retrieve=true
     *      2500000 element inserted/retrieved in 4058 ms
     *      2500000 element inserted/retrieved in 2184 ms
     *      2500000 element inserted/retrieved in 2342 ms
     *      2500000 element inserted/retrieved in 1884 ms
     *      2500000 element inserted/retrieved in 2044 ms
     *      For the map [class java.util.Hashtable] the average times is 2502 ms
     *      Start pressure testing the map [class java.util.Collections$SynchronizedMap] use the threshold [5],retrieve=true
     *      2500000 element inserted/retrieved in 2354 ms
     *      2500000 element inserted/retrieved in 2107 ms
     *      2500000 element inserted/retrieved in 2094 ms
     *      2500000 element inserted/retrieved in 2217 ms
     *      2500000 element inserted/retrieved in 1995 ms
     *      For the map [class java.util.Collections$SynchronizedMap] the average times is 2153 ms
     *      Start pressure testing the map [class java.util.Collections$SynchronizedMap] use the threshold [5],retrieve=false
     *      2500000 element inserted/retrieved in 1584 ms
     *      2500000 element inserted/retrieved in 1147 ms
     *      2500000 element inserted/retrieved in 1438 ms
     *      2500000 element inserted/retrieved in 1333 ms
     *      2500000 element inserted/retrieved in 1163 ms
     *      For the map [class java.util.Collections$SynchronizedMap] the average times is 1333 ms
     *      Start pressure testing the map [class java.util.concurrent.ConcurrentHashMap] use the threshold [5],retrieve=false
     *      2500000 element inserted/retrieved in 1120 ms
     *      2500000 element inserted/retrieved in 1131 ms
     *      2500000 element inserted/retrieved in 770 ms
     *      2500000 element inserted/retrieved in 987 ms
     *      2500000 element inserted/retrieved in 1008 ms
     *      For the map [class java.util.concurrent.ConcurrentHashMap] the average times is 1003 ms
     *      Start pressure testing the map [class java.util.concurrent.ConcurrentHashMap] use the threshold [5],retrieve=true
     *      2500000 element inserted/retrieved in 1239 ms
     *      2500000 element inserted/retrieved in 1233 ms
     *      2500000 element inserted/retrieved in 1153 ms
     *      2500000 element inserted/retrieved in 1187 ms
     *      2500000 element inserted/retrieved in 1243 ms
     *      For the map [class java.util.concurrent.ConcurrentHashMap] the average times is 1211 ms
     * ================================
     *
     *
     * </pre>

     *Single thread testing report
     *<pre>
     *     Start pressure testing the map [class java.util.Hashtable] use the threshold [1],retrieve=false
     * 500000 element inserted/retrieved in 1119 ms
     * 500000 element inserted/retrieved in 562 ms
     * 500000 element inserted/retrieved in 1999 ms
     * 500000 element inserted/retrieved in 588 ms
     * 500000 element inserted/retrieved in 545 ms
     * For the map [class java.util.Hashtable] the average times is 962 ms
     * Start pressure testing the map [class java.util.Hashtable] use the threshold [1],retrieve=true
     * 500000 element inserted/retrieved in 1250 ms
     * 500000 element inserted/retrieved in 3297 ms
     * 500000 element inserted/retrieved in 1211 ms
     * 500000 element inserted/retrieved in 466 ms
     * 500000 element inserted/retrieved in 1113 ms
     * For the map [class java.util.Hashtable] the average times is 1467 ms
     * Start pressure testing the map [class java.util.Collections$SynchronizedMap] use the threshold [1],retrieve=true
     * 500000 element inserted/retrieved in 1006 ms
     * 500000 element inserted/retrieved in 483 ms
     * 500000 element inserted/retrieved in 836 ms
     * 500000 element inserted/retrieved in 571 ms
     * 500000 element inserted/retrieved in 559 ms
     * For the map [class java.util.Collections$SynchronizedMap] the average times is 691 ms
     * Start pressure testing the map [class java.util.Collections$SynchronizedMap] use the threshold [1],retrieve=false
     * 500000 element inserted/retrieved in 220 ms
     * 500000 element inserted/retrieved in 584 ms
     * 500000 element inserted/retrieved in 222 ms
     * 500000 element inserted/retrieved in 228 ms
     * 500000 element inserted/retrieved in 242 ms
     * For the map [class java.util.Collections$SynchronizedMap] the average times is 299 ms
     * Start pressure testing the map [class java.util.concurrent.ConcurrentHashMap] use the threshold [1],retrieve=false
     * 500000 element inserted/retrieved in 402 ms
     * 500000 element inserted/retrieved in 277 ms
     * 500000 element inserted/retrieved in 250 ms
     * 500000 element inserted/retrieved in 509 ms
     * 500000 element inserted/retrieved in 236 ms
     * For the map [class java.util.concurrent.ConcurrentHashMap] the average times is 334 ms
     * Start pressure testing the map [class java.util.concurrent.ConcurrentHashMap] use the threshold [1],retrieve=true
     * 500000 element inserted/retrieved in 321 ms
     * 500000 element inserted/retrieved in 532 ms
     * 500000 element inserted/retrieved in 287 ms
     * 500000 element inserted/retrieved in 301 ms
     * 500000 element inserted/retrieved in 542 ms
     * For the map [class java.util.concurrent.ConcurrentHashMap] the average times is 396 ms
     * Start pressure testing the map [class java.util.HashMap] use the threshold [1],retrieve=false
     * 500000 element inserted/retrieved in 203 ms
     * 500000 element inserted/retrieved in 249 ms
     * 500000 element inserted/retrieved in 208 ms
     * 500000 element inserted/retrieved in 444 ms
     * 500000 element inserted/retrieved in 194 ms
     * For the map [class java.util.HashMap] the average times is 259 ms
     * Start pressure testing the map [class java.util.HashMap] use the threshold [1],retrieve=true
     * 500000 element inserted/retrieved in 270 ms
     * 500000 element inserted/retrieved in 552 ms
     * 500000 element inserted/retrieved in 261 ms
     * 500000 element inserted/retrieved in 257 ms
     * 500000 element inserted/retrieved in 258 ms
     * For the map [class java.util.HashMap] the average times is 319 ms
     * ================================
     *</pre>
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        /**
         * Hashtable:线程安全：synchronized,性能不好
         */
 /*       pressureTest(new Hashtable<String, Integer>(),5,false);
        pressureTest(new Hashtable<String, Integer>(),5,true);

        pressureTest(Collections.synchronizedMap(new HashMap<String, Integer>()),5,true);
        pressureTest(Collections.synchronizedMap(new HashMap<String, Integer>()),5,false);


        pressureTest(new ConcurrentHashMap<String, Integer>(),5,false);
        pressureTest(new ConcurrentHashMap<String, Integer>(),5,true);
        System.out.println("================================");*/


        pressureTest(new Hashtable<String, Integer>(),1,false);
        pressureTest(new Hashtable<String, Integer>(),1,true);

        pressureTest(Collections.synchronizedMap(new HashMap<String, Integer>()),1,true);
        pressureTest(Collections.synchronizedMap(new HashMap<String, Integer>()),1,false);


        pressureTest(new ConcurrentHashMap<String, Integer>(),1,false);
        pressureTest(new ConcurrentHashMap<String, Integer>(),1,true);

        pressureTest(new HashMap<String, Integer>(),1,false);
        pressureTest(new HashMap<String, Integer>(),1,true);
        System.out.println("================================");


    }

    /**
     * todo 压力测试
     * @param map
     * @param threshold 几个线程同时工作
     * @param retrieve true:插入并获取 false:插入
     * @throws InterruptedException
     */
    private static void pressureTest(final Map<String, Integer> map, int threshold, final boolean retrieve) throws InterruptedException {
        System.out.println("Start pressure testing the map [" + map.getClass() + "]" + " use the threshold [" + threshold + "],retrieve=" + retrieve);
        long totalTime = 0L;
        final int MAX_THRESHOLD = 500000;
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(threshold);
            for (int j = 0; j < threshold; j++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int k = 0; k < MAX_THRESHOLD; k++) {
                            Integer randomNumber = (int) Math.ceil(Math.random() * 600000);
                            if (retrieve) map.get(String.valueOf(randomNumber));
                            map.put(String.valueOf(randomNumber), randomNumber);
                        }
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.HOURS);
            long endTime = System.nanoTime();
            long period = (endTime - startTime) / 1000000L;
            System.out.println(threshold * MAX_THRESHOLD + " element inserted/retrieved in " + period + " ms");
            totalTime += period;
        }

        System.out.println("For the map [" + map.getClass() + "] the average times is " + (totalTime / 5) + " ms");

    }
}
