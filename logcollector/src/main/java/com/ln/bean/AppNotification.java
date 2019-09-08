package com.ln.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/1 18:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppNotification {
    private String action;
    private String type;
    private String ap_time;
    private String content;
}
