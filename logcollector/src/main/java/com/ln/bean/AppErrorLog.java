package com.ln.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/1 18:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppErrorLog {

    private String errorBrief;
    private String errorDetail;
}
