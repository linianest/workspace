package com.ln.juc.utils;

import java.util.Random;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.utils
 * @Name:RandomUtils
 * @Author:linianest
 * @CreateTime:2021/1/16 11:32
 * @version:1.0
 * @Description TODO:
 */
public class RandomUtils {
    private static final Random random = new Random(System.currentTimeMillis());

    public static Random getRandom() {

        return random;
    }
}
