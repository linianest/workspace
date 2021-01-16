package com.ln.concurrent.chapter6;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter6
 * @version: 1.0
 */

import java.util.Random;

/**
 * @ClassName:WriterWorker
 * @Author:linianest
 * @CreateTime:2020/3/24 16:29
 * @version:1.0
 * @Description TODO: 多线程读数据
 */
public class WriterWorker extends Thread {

    private static final Random random = new Random(System.currentTimeMillis());

    private final SharedData data;
    private final String filler;
    private int index = 0;

    public WriterWorker(SharedData data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true){
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(1_000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length())
            index = 0;
        return c;
    }
}
