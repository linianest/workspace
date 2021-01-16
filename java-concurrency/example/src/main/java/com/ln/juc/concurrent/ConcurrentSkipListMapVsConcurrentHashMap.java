package com.ln.juc.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.concurrent
 * @Name:ConcurrentSkipListMapVsConcurrentHashMap
 * @Author:linianest
 * @CreateTime:2021/1/13 17:45
 * @version:1.0
 * @Description TODO:ConcurrentSkipListMap Vs ConcurrentHashMap
 */

public class ConcurrentSkipListMapVsConcurrentHashMap {

    static class Entry {
        int threshold;
        long ms;

        public Entry(int threshold, long ms) {
            this.threshold = threshold;
            this.ms = ms;
        }

        @Override
        public String toString() {
            return "Count:" + threshold + ":ms:" + ms;
        }
    }

    private final static Map<Class<?>, List<Entry>> summary = new HashMap<Class<?>, List<Entry>>() {
        {
            put(ConcurrentHashMap.class, new ArrayList<>());
            put(ConcurrentSkipListMap.class, new ArrayList<>());
        }
    };


    /**
     * ConcurrentHashMap
     * Count:10:ms:664
     * Count:20:ms:384
     * Count:30:ms:432
     * Count:40:ms:441
     * Count:50:ms:386
     * Count:60:ms:363
     * Count:70:ms:378
     * Count:80:ms:330
     * Count:90:ms:349
     * Count:100:ms:406
     * ===========================
     * ConcurrentSkipListMap
     * Count:10:ms:2322
     * Count:20:ms:1245
     * Count:30:ms:1450
     * Count:40:ms:1451
     * Count:50:ms:1475
     * Count:60:ms:1436
     * Count:70:ms:1457
     * Count:80:ms:1430
     * Count:90:ms:1491
     * Count:100:ms:1506
     * ===========================
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        for (int i = 10; i <= 100; i += 10) {
            pressureTest(new ConcurrentHashMap<String, Integer>(), i);
            pressureTest(new ConcurrentSkipListMap<>(), i);
        }
     /*   pressureTest(new ConcurrentHashMap<String, Integer>(), 10);
        pressureTest(new ConcurrentSkipListMap<>(), 10);
        System.out.println("================================");*/
        summary.forEach((k, v) -> {
            System.out.println(k.getSimpleName());
            v.forEach(System.out::println);
            System.out.println("===========================");
        });
    }

    /**
     * todo 压力测试
     *
     * @param map
     * @param threshold 几个线程同时工作
     * @throws InterruptedException
     */
    private static void pressureTest(final Map<String, Integer> map, int threshold) throws InterruptedException {
        System.out.println("Start pressure testing the map [" + map.getClass() + "]" + " use the threshold [" + threshold + "]");
        long totalTime = 0L;
        final int MAX_THRESHOLD = 500000;
        for (int i = 0; i < 5; i++) {
            final AtomicInteger counter = new AtomicInteger(0);
            long startTime = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(threshold);
            for (int j = 0; j < threshold; j++) {
                executorService.execute(() -> {
                    for (int k = 0; k < MAX_THRESHOLD && counter.getAndIncrement() < MAX_THRESHOLD; k++) {
                        Integer randomNumber = (int) Math.ceil(Math.random() * 600000);
                        map.get(String.valueOf(randomNumber));
                        map.put(String.valueOf(randomNumber), randomNumber);
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.HOURS);
            long endTime = System.nanoTime();
            long period = (endTime - startTime) / 1000000L;
            System.out.println(MAX_THRESHOLD + " element inserted/retrieved in " + period + " ms");
            totalTime += period;
        }

        summary.get(map.getClass()).add(new Entry(threshold, (totalTime / 5)));
        System.out.println("For the map [" + map.getClass() + "] the average times is " + (totalTime / 5) + " ms");
    }


}
