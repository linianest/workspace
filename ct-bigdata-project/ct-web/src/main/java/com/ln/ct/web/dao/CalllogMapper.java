package com.ln.ct.web.dao;

import com.ln.ct.web.bean.Calllog;

import java.util.List;
import java.util.Map;

/**
 * 通话日志数据访问对象
 */
public interface CalllogMapper {
    List<Calllog> queryMonthDatas(Map<String, Object> paramMap);
}
