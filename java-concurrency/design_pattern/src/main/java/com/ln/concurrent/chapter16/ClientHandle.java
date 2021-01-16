package com.ln.concurrent.chapter16;

import java.io.*;
import java.net.Socket;
import java.util.stream.Stream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter16
 * @Name:ClientHandle
 * @Author:linianest
 * @CreateTime:2021/1/4 12:07
 * @version:1.0
 * @Description TODO: 客户端
 */
public class ClientHandle implements Runnable {
    private final Socket socket;
    private volatile boolean running = true;

    public ClientHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        /**
         * 使用try resource方式，出现异常，数据流关闭
         */
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             final PrintWriter printWriter = new PrintWriter(outputStream);) {
            while (running) {
                final String message = reader.readLine();
                if (null == message) {
                    break;
                }
                System.out.println("Come from client >" + message);
                printWriter.write("echo " + message + "\n");
                // 将数据刷到管道中
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.running = false;
        } finally {
            this.stop();
        }

    }

    public void stop() {
        if (running) {
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
}
