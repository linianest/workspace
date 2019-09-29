package com.ln.ct.common.bean;

import java.io.Closeable;

/**
 * @Description TODO  生产者接口
 * @AUTHOR LiNian
 * @DATE 2019/9/22 12:52
 */
public interface Producer extends Closeable {

    public void setIn(DataIn in);
    public void setOut(DataOut out);
    /**
     * 生产接口
     */
    public void produce();
}
