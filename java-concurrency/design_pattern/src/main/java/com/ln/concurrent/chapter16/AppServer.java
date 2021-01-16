package com.ln.concurrent.chapter16;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter15
 * @Name:AppServer
 * @Author:linianest
 * @CreateTime:2021/1/4 11:50
 * @version:1.0
 * @Description TODO: 请求后台数据，后台出现异常，终结线程并释放请求的线程资源，会用到前面学的Thread-pre-Message模式
 */
public class AppServer extends Thread {
    private int port;
    private static final int DEFULT_PORT = 12722;
    private volatile boolean start = true;
    private List<ClientHandle> clientHandlers = new ArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private ServerSocket server;

    public AppServer() {
        this(DEFULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            this.server = new ServerSocket(port);
            while (start) {
                Socket client = this.server.accept();
                ClientHandle clientHandler = new ClientHandle(client);
                executor.submit(clientHandler);
                this.clientHandlers.add(clientHandler);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.dispose();
        }
    }

    /**
     * 释放资源
     */
    private void dispose() {
        this.clientHandlers.stream().forEach(ClientHandle::stop);
        this.executor.shutdown();
    }

    public void shutdown() throws IOException {
        this.start = false;
        this.server.close();
        this.interrupt();
    }
}
