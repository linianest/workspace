package com.ln.concurrency.chapter13;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter13
 * @version: 1.0
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @ClassName:SimpleThreadPool
 * @Author:linianest
 * @CreateTime:2020/3/20 18:02
 * @version:1.0
 * @Description TODO: 自定义线程池
 */

/**
 * 1、线程队列
 * 2、拒绝策略：当达到阈值，就拒绝接受任务
 * 3、丢弃
 * 4、阻塞
 */
public class SimpleThreadPool extends Thread {

    // define thread pool size
    private int size;
    // thread collection number
    private static final int THREADPOOL_MIN = 4;
    private static final int THREADPOOL_ACTIVATE = 8;
    private static final int THREADPOOL_MAX = 12;
    // task queue size
    private final int queueSize;
    // define task queue size
    public static final int DEFAULT_TASK_QUEUE_SIZE = 2000;

    // customize task queue
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
    // define the thread sequence
    private static volatile int seq = 0;

    // define the thread name prefix
    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    // create a thread group
    public static final ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    // create a collections of task
    public static final List<WorkerTask> THREAD_QUEUE = new ArrayList<>();
    // define default exception
    public final DiscardPoliy discardpoliy;

    private volatile boolean destroy = false;

    public static final DiscardPoliy DEFAULT_DISCARD_POLIY = () -> {
        throw new DiscardException("Discard this Task.");
    };
    // ThreadPool instance start min size
    private int min;
    private int max;
    private int active;

    public SimpleThreadPool() {
        this(THREADPOOL_MIN, THREADPOOL_ACTIVATE, THREADPOOL_MAX, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLIY);
    }

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPoliy discardPoliy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardpoliy = discardPoliy;
        init();
    }

    private void init() {
        for (int i = 0; i < this.min; i++) {
            createWorkTask();
        }
        this.size = min;
        this.start();
    }

    private void submit(Runnable runnable) {
        if (destroy)
            throw new IllegalStateException("The thread pool already destroy and not allow submit task.");
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queueSize)
                discardpoliy.discard();
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    public int getQueueSize() {
        return queueSize;
    }

    public int getSize() {
        return size;
    }

    public boolean isDestroy() {
        return this.destroy;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }
        synchronized (THREAD_QUEUE) {
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0) {
                for (WorkerTask task : THREAD_QUEUE) {
                    if (task.taskState == TaskState.BLOCKED) {
                        task.interrupt();
                        task.close();
                        initVal--;
                    } else {
                        Thread.sleep(10);
                    }
                }
            }
        }
        System.out.println(GROUP.activeCount());
        this.destroy = true;
        System.out.println("The thread pool disposed. ");
    }


    @Override
    public void run() {
        while (!destroy) {
            System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d\n",
                    this.min, this.active, this.max, this.size, this.TASK_QUEUE.size());

            try {
                // increment thread
                Thread.sleep(5_000L);
                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to active.");
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to max.");
                    size = max;
                }
                synchronized (THREAD_QUEUE) {
                    // release thread pool
                    if (TASK_QUEUE.isEmpty() && size > active) {
                        System.out.println("=========Reduce==========");
                        int releaseSize = size - active;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0)
                                break;
                            WorkerTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * create thread and add task to thread queue
     */
    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    public static class DiscardException extends RuntimeException {
        DiscardException(String message) {
            super(message);
        }
    }

    public interface DiscardPoliy {
        void discard() throws DiscardException;
    }

    private static class WorkerTask extends Thread {
        private volatile TaskState taskState = TaskState.FREE;

        /**
         * customize thread
         *
         * @param group
         * @param name
         */
        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        /**
         * @return currentTask state
         */
        public TaskState getTaskState() {
            return this.taskState;
        }

        /**
         * running task
         */
        public void run() {

            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {

                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Closed.");
                            break OUTER;
                        }
                    }
                    // get a task from task queue
                    runnable = TASK_QUEUE.removeFirst();
                }
                if (runnable != null) {
                    // task before running
                    taskState = TaskState.RUNNING;
                    // task running
                    runnable.run();
                    // task finish
                    taskState = TaskState.FREE;
                }
            }
        }

        /**
         * task close
         */
        public void close() {
            this.taskState = TaskState.DEAD;
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        SimpleThreadPool threadPool = new SimpleThreadPool(6, 10, SimpleThreadPool.DEFAULT_DISCARD_POLIY);
        SimpleThreadPool threadPool = new SimpleThreadPool();

//        for (int i = 0; i < 40; i++) {
//            threadPool.submit(() -> {
//                System.out.println("the runnable be serviced by " + Thread.currentThread() + " start...");
//                try {
//                    Thread.sleep(1_000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("the runnable be serviced by " + Thread.currentThread() + " finished...");
//            });
//            System.out.println("==================");
//        }

        IntStream.range(0, 40)
                .forEach(i -> {
                    threadPool.submit(() -> {
                        System.out.println("the runnable " + i + " be serviced by " + Thread.currentThread() + " start...");
                        try {
                            Thread.sleep(3_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("the runnable " + i + " be serviced by " + Thread.currentThread() + " finished...");
                    });
                });
        Thread.sleep(10_000);
        threadPool.shutdown();
//        threadPool.submit(() -> System.out.println("========"));
    }
}
