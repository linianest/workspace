package com.ln.ct.common.bean;

import java.io.Closeable;

/**
 * @Description TODO 消费者接口
 * @AUTHOR LiNian
 * @DATE 2019/9/22 18:16
 */
public interface Consumer extends Closeable {

    /**
     * 消费数据
     */
    public void consume();
}
