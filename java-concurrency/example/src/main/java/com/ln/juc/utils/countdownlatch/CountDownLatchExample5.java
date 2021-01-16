package com.ln.juc.utils.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.utils.countdownlatch
 * @Name:CountDownLatchExample3
 * @Author:linianest
 * @CreateTime:2021/1/16 13:10
 * @version:1.0
 * @Description TODO: 将海量数据从表中搬迁到数据湖中，搬迁完成后进行数据验证，验证搬迁后的数据完成性
 */

public class CountDownLatchExample5 {


    private static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Event[] events = {new Event(1), new Event(2)};
        // 总体事件
        final CountDownLatch latch = new CountDownLatch(events.length);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (Event event : events) {
            List<Table> tables = capture(event);

            TaskGroup taskGroup = new TaskGroup(tables.size(), event, latch);
            for (Table table : tables) {

                TaskBatch taskBatch = new TaskBatch(2, taskGroup);
                TrustSourceColumns columnsRunnable = new TrustSourceColumns(table, taskBatch);
                TrustSourceRecordCount recordCountRunnable = new TrustSourceRecordCount(table, taskBatch);

                executorService.submit(columnsRunnable);
                executorService.submit(recordCountRunnable);
            }
        }
        latch.await();
        System.out.println("all of the work finish done.");
        executorService.shutdown();

    }

    interface Watcher {
        void startWatch();

        void done(Table table);
    }

    /**
     * TODO 单个任务表的事件监视器
     */
    static class TaskBatch implements Watcher {

        private CountDownLatch countDownLatch;
        private TaskGroup taskGroup;

        public TaskBatch(int size, TaskGroup taskGroup) {
            this.countDownLatch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }

        @Override
        public void startWatch() {
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("The table " + table.tableName + " finished work,[" + table + "]");
                taskGroup.done(table);
            }
        }

    }

    /**
     * TODO 单个事件的监视器
     */
    static class TaskGroup implements Watcher {

        private CountDownLatch countDownLatch;
        private Event event;
        private CountDownLatch totalEvent;

        public TaskGroup(int size, Event event, CountDownLatch totalEvent) {
            this.event = event;
            this.countDownLatch = new CountDownLatch(size);
            this.totalEvent = totalEvent;
        }

        @Override
        public void startWatch() {
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
//                System.out.println("The table " + table.tableName + " finished work,[" + table + "]");
                System.out.println("======All of table done in event: " + event.id);
                // 完成一个事件任务
                totalEvent.countDown();
            }
        }

    }

    /**
     * 验证数据的记录条数
     */
    static class TrustSourceRecordCount implements Runnable {

        private final Table table;
        private final TaskBatch taskBatch;

        public TrustSourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.sourceRecordCount;
//            System.out.println("The table " + table.tableName + " target record count capture done and update the data.");
            taskBatch.done(table);
        }
    }

    /**
     * 验证数据的字段数据
     */
    static class TrustSourceColumns implements Runnable {

        private final Table table;
        private final TaskBatch taskBatch;


        public TrustSourceColumns(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.sourceColumnSchema;
//            System.out.println("The table " + table.tableName + " target columns capture done and update the data.");
            taskBatch.done(table);
        }
    }

    static class Event {

        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    /**
     *
     */
    static class Table {
        String tableName;
        long sourceRecordCount = 10;
        long targetCount;
        String sourceColumnSchema = "<table name='A'><column name='coll' type='varchar2'></column></table>";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> capture(Event event) {

        List<Table> list = new ArrayList<>();
        IntStream.range(0, 10).boxed()
                .forEach(i -> {
                    list.add(new Table("table-" + event.id + "-" + i, i * 1000));
                });
        return list;
    }

}
