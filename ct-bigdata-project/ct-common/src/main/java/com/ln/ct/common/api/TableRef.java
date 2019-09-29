package com.ln.ct.common.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/26 20:15
 */
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableRef {
    String value();
}
