package com.ln.ct.producer.io;

import com.ln.ct.common.bean.Data;
import com.ln.ct.common.bean.DataIn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO 本地文件数据输入
 * @AUTHOR LiNian
 * @DATE 2019/9/22 13:11
 */
public class LocalFileDataIn implements DataIn {

    private BufferedReader reader;

    public LocalFileDataIn(String path) {
        setPath(path);
    }

    @Override
    public void setPath(String path) {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object read() throws IOException {
        return null;
    }

    /**
     * 读取数据，返回集合
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    @Override
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {

        // 从文件中读取所有数据
        ArrayList<T> ts = new ArrayList<>();
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // 将数据转换为指定的对象，封装为集合返回
                T t = clazz.newInstance();
                t.setValue(line);
                ts.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ts;
    }
    /**
     * 关闭资源
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if (reader!=null){
            reader.close();
        }

    }
}
