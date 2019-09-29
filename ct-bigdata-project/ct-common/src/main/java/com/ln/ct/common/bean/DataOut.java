package com.ln.ct.common.bean;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/22 12:54
 */
public interface DataOut extends Closeable {
    public void setPath(String path);

    public void write(Object data) throws IOException;
    public void write(String data) throws IOException;
}
