package com.ln.ct.common.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/26 20:24
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Rowkey {
}
