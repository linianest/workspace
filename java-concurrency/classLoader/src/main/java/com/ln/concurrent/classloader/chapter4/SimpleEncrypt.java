package com.ln.concurrent.classloader.chapter4;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.classloader.chapter4
 * @Name:SimpleEncrypt
 * @Author:linianest
 * @CreateTime:2021/1/6 14:39
 * @version:1.0
 * @Description TODO: 通过亦或的方式加密解密
 */
public class SimpleEncrypt {
    // 明文
    public static final String plain = "hello classLoader";
    // 秘钥
    public static final byte ENCRYPT_FACTOR = (byte) 0xff;

    //亦或的方式加密解密
    public static void main(String[] args) {
        // 加密
        final byte[] bytes = plain.getBytes();
        final byte[] encrypt = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            encrypt[i] = (byte) (bytes[i] ^ ENCRYPT_FACTOR);
        }
        System.out.println(new String(encrypt));
        // 解密
        final byte[] dencrypt = new byte[encrypt.length];
        for (int i = 0; i < encrypt.length; i++) {
            dencrypt[i] = (byte) (encrypt[i] ^ ENCRYPT_FACTOR);
        }
        System.out.println(new String(dencrypt));
    }

}
