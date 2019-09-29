package com.ln.ct.producer.io;

import com.ln.ct.common.bean.DataOut;

import java.io.*;

/**
 * @Description TODO 本地文件数据输出
 * @AUTHOR LiNian
 * @DATE 2019/9/22 13:11
 */
public class LocalFileDataOut implements DataOut {

    private PrintWriter writer = null;

    public LocalFileDataOut(String path) {
        setPath(path);
    }

    @Override
    public void setPath(String path) {

        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Object data) throws IOException {
        write(data.toString());
    }

    /**
     * 将数据文件生成到文件中
     *
     * @param data
     * @throws IOException
     */
    @Override
    public void write(String data) throws IOException {
        writer.println(data);
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
