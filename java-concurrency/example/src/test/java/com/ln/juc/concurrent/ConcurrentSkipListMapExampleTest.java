package com.ln.juc.concurrent;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.concurrent
 * @Name:ConcurrentSkipListMapExampleTest
 * @Author:linianest
 * @CreateTime:2021/1/13 17:08
 * @version:1.0
 * @Description TODO:
 */
public class ConcurrentSkipListMapExampleTest {

    /**
     * 1.1     2
     * 1.5     2
     * <p>
     * 1.1 ceiling     2
     * 1.9 ceiling     2
     * <p>
     * 1.1 floor      1
     * 1.9 floor      1
     */
    @Test
    public void testCeiling() {
        final ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");
        assertThat(map.size(), equalTo(3));
        assertThat(map.ceilingKey(2), equalTo(5));
        assertThat(map.ceilingEntry(2).getValue(), equalTo("Java"));
        assertThat(map.ceilingEntry(5).getValue(), equalTo("Java"));
    }

    @Test
    public void testFloor() {
        final ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");
        assertThat(map.size(), equalTo(3));
        assertThat(map.floorKey(2), equalTo(1));
        assertThat(map.floorEntry(2).getValue(), equalTo("Scala"));
        assertThat(map.floorEntry(1).getValue(), equalTo("Scala"));
    }

    @Test
    public void testFirst() {
        final ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");
        assertThat(map.size(), equalTo(3));
        assertThat(map.firstKey(), equalTo(1));
        assertThat(map.firstEntry().getValue(), equalTo("Scala"));
    }

    @Test
    public void testLast() {
        final ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");
        assertThat(map.size(), equalTo(3));
        assertThat(map.lastKey(), equalTo(10));
        assertThat(map.lastEntry().getValue(), equalTo("Clojure"));
    }

    @Test
    public void testMerge() {
        final ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");
        /**
         * ov = original value
         * v=specify value
         */
        String result = map.merge(1, "c++", (ov, v) -> {
            assertThat(ov, equalTo("Scala"));
            assertThat(v, equalTo("c++"));
            return ov + v;
        });
        assertThat(result, equalTo("Scalac++"));
        assertThat(map.get(1), equalTo("Scalac++"));
    }

    @Test
    public void testCompute() {
        final ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");
        /**
         * ov = original value
         * v=specify value
         */
        String result = map.compute(1, (k, v) -> {
            assertThat(k, equalTo(1));
            assertThat(v, equalTo("Scala"));
            return "Hello";
        });
        assertThat(result, equalTo("Hello"));
        assertThat(map.get(1), equalTo("Hello"));
    }

}