package com.ln.concurrent.classloader.chapter4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.classloader.chapter4
 * @Name:EncryptUtils
 * @Author:linianest
 * @CreateTime:2021/1/6 14:47
 * @version:1.0
 * @Description TODO: 加密的工具类
 */
public abstract class EncryptUtils {
    // 秘钥
    private static final byte ENCRYPT_FACTOR = (byte) 0xff;

    private EncryptUtils() {
    }

    // 加密
    public static void doEncrypt(String source, String target) {
        try (final FileInputStream fileInputStream = new FileInputStream(source); final FileOutputStream fileOutputStream = new FileOutputStream(target)) {
            int data;
            while ((data = fileInputStream.read()) != -1) {
                fileOutputStream.write(data ^ ENCRYPT_FACTOR);
            }
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
