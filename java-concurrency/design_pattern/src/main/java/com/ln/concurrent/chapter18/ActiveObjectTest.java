package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:ActiveObjectTest
 * @Author:linianest
 * @CreateTime:2021/1/4 16:17
 * @version:1.0
 * @Description TODO: 测试异步消息的主动对象
 */
public class ActiveObjectTest {
    public static void main(String[] args) {
        final ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread(activeObject,"Alice").start();
        new MakerClientThread(activeObject,"Baobo").start();

        new DisPlayClientThread("Chris",activeObject).start();
    }
}
