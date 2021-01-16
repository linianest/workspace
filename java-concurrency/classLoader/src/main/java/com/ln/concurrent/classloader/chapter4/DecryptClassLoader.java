package com.ln.concurrent.classloader.chapter4;

import java.io.*;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.classloader.chapter4
 * @Name:DecryptClassLoader
 * @Author:linianest
 * @CreateTime:2021/1/6 15:06
 * @version:1.0
 * @Description TODO: 解密类加载器
 */
public class DecryptClassLoader extends ClassLoader {
    // 秘钥
    private static final byte ENCRYPT_FACTOR = (byte) 0xff;
    private static final String DEFULT_DIR = "E:\\";
    private String dir = DEFULT_DIR;

    public DecryptClassLoader() {
        super();
    }

    public DecryptClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        final String classpath = name.replace(".", "/");
        final File classfile = new File(dir, classpath + ".class");
        if (!classfile.exists()) {
            throw new ClassNotFoundException("The class " + name + " not found under directory [" + dir + "]");
        }
        byte[] classBytes = loadClassBytes(classfile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("load the class " + name + " failed");
        }

        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    /**
     * 将文件读取到内存字节数组并加密后，输出流中
     * @param classfile
     * @return
     */
    private byte[] loadClassBytes(File classfile) {
        try(final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final FileInputStream fileInputStream = new FileInputStream(classfile);){
            int data;
            while ((data = fileInputStream.read()) != -1) {
                byteArrayOutputStream.write(data ^ ENCRYPT_FACTOR);
            }
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }  catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
