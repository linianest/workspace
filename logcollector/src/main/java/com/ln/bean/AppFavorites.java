package com.ln.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/1 20:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppFavorites {
    private int id;
    private int course_id;
    private int userid;
    private String add_time;
}
