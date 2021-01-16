package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:Servamt
 * @Author:linianest
 * @CreateTime:2021/1/4 15:19
 * @version:1.0
 * @Description TODO: 真正做事的
 */
class Servant implements ActiveObject {
    /**
     * 返回真正的结果
     * @param count
     * @param fillChar
     * @return
     */
    @Override
    public Result makeString(int count, char fillChar) {

        char[] buf = new char[count];
        for (int i = 0; i < count; i++) {
            buf[i]=fillChar;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new RealResult(new String(buf));
    }

    /**
     * 展示结果
     * @param text
     */
    @Override
    public void displayString(String text) {
        try {
            System.out.println("Display:"+text);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
