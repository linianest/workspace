package com.ln.juc.blocking;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import sun.font.CoreMetrics;

import java.util.concurrent.ArrayBlockingQueue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.blocking
 * @Name:ArrayBlockingQueueExampleTest
 * @Author:linianest
 * @CreateTime:2021/1/12 14:33
 * @version:1.0
 * @Description TODO:
 */
public class ArrayBlockingQueueExampleTest {

    private ArrayBlockingQueueExample example;

    @Before
    public void setUp() {
        example = new ArrayBlockingQueueExample();
    }

    @Test
    public void tearDown() {
        example = null;
    }

    /**
     * Inserts the specified element at the tail of this queue if it is
     * possible to do so immediately without exceeding the queue's capacity,
     * returning {@code true} upon success and throwing an
     * {@code IllegalStateException} if this queue is full.
     */
    @Test
    public void testAddMethodNotExceedCapacity() {

        ArrayBlockingQueue<String> queue = example.create(5);
        assertThat(queue.add("Hello1"), equalTo(true));
        assertThat(queue.add("Hello2"), equalTo(true));
        assertThat(queue.add("Hello3"), equalTo(true));
        assertThat(queue.add("Hello4"), equalTo(true));
        assertThat(queue.add("Hello5"), equalTo(true));
        assertThat(queue.size(), equalTo(5));
    }

    @Test(expected = IllegalStateException.class)
    public void testAddMethodExceedCapacity() {

        ArrayBlockingQueue<String> queue = example.create(5);
        assertThat(queue.add("Hello1"), equalTo(true));
        assertThat(queue.add("Hello2"), equalTo(true));
        assertThat(queue.add("Hello3"), equalTo(true));
        assertThat(queue.add("Hello4"), equalTo(true));
        assertThat(queue.add("Hello5"), equalTo(true));
        assertThat(queue.add("Hello6"), equalTo(true));
        fail("should not process to here.");
    }
    @Test
    public void testOfferMethodNotExceedCapacity() {

        ArrayBlockingQueue<String> queue = example.create(5);
        assertThat(queue.offer("Hello1"), equalTo(true));
        assertThat(queue.offer("Hello2"), equalTo(true));
        assertThat(queue.offer("Hello3"), equalTo(true));
        assertThat(queue.offer("Hello4"), equalTo(true));
        assertThat(queue.offer("Hello5"), equalTo(true));
        assertThat(queue.size(), equalTo(5));
    }
    @Test
    public void testOfferMethodExceedCapacity() {

        ArrayBlockingQueue<String> queue = example.create(5);
        assertThat(queue.offer("Hello1"), equalTo(true));
        assertThat(queue.offer("Hello2"), equalTo(true));
        assertThat(queue.offer("Hello3"), equalTo(true));
        assertThat(queue.offer("Hello4"), equalTo(true));
        assertThat(queue.offer("Hello5"), equalTo(true));
        assertThat(queue.offer("Hello6"), equalTo(false));
        assertThat(queue.size(), equalTo(5));
    }
    @Test
    public void testPutMethodNotExceedCapacity() throws InterruptedException {

        ArrayBlockingQueue<String> queue = example.create(5);
        queue.put("Hello1");
        queue.put("Hello2");
        queue.put("Hello3");
        queue.put("Hello4");
        queue.put("Hello5");
        assertThat(queue.size(), equalTo(5));
    }
    @Test
    public void testPutMethodExceedCapacity() throws InterruptedException {

        ArrayBlockingQueue<String> queue = example.create(5);
        queue.put("Hello1");
        queue.put("Hello2");
        queue.put("Hello3");
        queue.put("Hello4");
        queue.put("Hello5");
        queue.put("Hello6");
        fail("should not process to here.");
    }
}