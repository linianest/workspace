package com.ln.concurrent.classloader.chapter5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.classloader
 * @Name:SimpleClassLoader
 * @Author:linianest
 * @CreateTime:2021/1/6 16:06
 * @version:1.0
 * @Description TODO: 自定义类加载器
 */
public class SimpleClassLoader extends ClassLoader {
    // 默认的加载路径
    private static final String DEFULT_DIR = "E:\\workspace\\java-concurrency\\classLoader\\target\\classes";

    private String dir = DEFULT_DIR;

    // 类加载器的名称
    private String classLoaderName;

    public SimpleClassLoader() {
        super();
    }

    public SimpleClassLoader(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    // 指定该类加载器的父亲是哪个
    public SimpleClassLoader(String classLoaderName, ClassLoader parent) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }


    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }



    /**
     *  如果包含java的内容，使用系统类加载器
     *  否则先用自定义的类加载器，加载不出来再用系统类加载器
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = null;
        if (name.startsWith("java.")) {
            try {
                final ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                clazz = systemClassLoader.loadClass(name);
                if (clazz != null) {
                    if (resolve) {
                        resolveClass(clazz);
                        return clazz;
                    }
                }
            } catch (Exception e) {
                //ignore
            }
        }
        try {
            clazz = this.findClass(name);
        } catch (ClassNotFoundException e) {
        }
        if (null == clazz && getParent() != null) {
            getParent().loadClass(name);
        }
        return clazz;
    }

    /**
     * 返回找的class文件字节数组
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        final String classpath = name.replace(".", "/");
        final File classfile = new File(dir, classpath + ".class");
        if (!classfile.exists()) {
            throw new ClassNotFoundException("The class " + name + " not found under directory [" + dir + "]");
        }
        // 将文件读取到字节数组中
        byte[] classBytes = loadClassBytes(classfile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("load the class " + name + " failed");
        }

        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    /**
     * 将文件读取到内存字节数组并加密后，输出流中
     *
     * @param classfile
     * @return
     */
    private byte[] loadClassBytes(File classfile) {
        try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             final FileInputStream fileInputStream = new FileInputStream(classfile);) {
            final byte[] buffer = new byte[1024];
            int len;
            // 将读取的数据流到缓冲区中并输出
            while ((len = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
