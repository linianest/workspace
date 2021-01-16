package com.ln.concurrent.chapter16;

import java.io.IOException;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter16
 * @Name:AppServerClient
 * @Author:linianest
 * @CreateTime:2021/1/4 13:08
 * @version:1.0
 * @Description TODO: 测试socket server连接
 */
public class AppServerClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        final AppServer server = new AppServer(13345);
        server.start();

        Thread.sleep(45_000L);
        server.shutdown();
    }
}
