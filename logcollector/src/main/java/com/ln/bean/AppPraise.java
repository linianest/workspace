package com.ln.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 点赞
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/1 18:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppPraise {

    private int id;
    private int userid;
    private int target_id;
    private int type;
    private String add_time;
}
