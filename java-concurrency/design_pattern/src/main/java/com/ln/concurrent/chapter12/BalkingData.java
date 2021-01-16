package com.ln.concurrent.chapter12;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter12
 * @Name:BalkingData
 * @Author:linianest
 * @CreateTime:2021/1/3 17:15
 * @version:1.0
 * @Description TODO:
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 使用场景：当你去小酒馆消费，当你召唤服务生后，有好几个服务生看到，当一个远些的服务生正准备去服务时，
 * 近些的服务生发现后去服务，远一些的服务生发现后，就放弃此次服务（任务数据的状态已经发生改变）
 */
public class BalkingData {

    private final String fileName;
    private String content;
    private boolean changed;

    public BalkingData(String fileName, String content, boolean changed) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    /**
     * change方法需要和save方法成对出现，如果线程执行change执行多次，中间的数据会被覆盖丢失掉
     * @param newContext
     */
    public synchronized void change(String newContext) {
        this.content = newContext;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls do save,content=" + content);
        // try方法本身实现closeable接口，如果出现异常会关闭连接
        try (Writer writer = new FileWriter(fileName, true)) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }
    }
}
