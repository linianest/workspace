package com.ln.ct.common.bean;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/22 12:54
 */
public interface DataIn extends Closeable {

    public void setPath(String path);

    public Object read() throws IOException;
    public <T extends Data>List<T> read(Class<T> clazz) throws IOException;
}
