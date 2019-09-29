package com.ln.ct.analysis;

import com.ln.ct.analysis.tool.AnalysisBeanTool;
import com.ln.ct.analysis.tool.AnalysisTextTool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @Description TODO 分析数据
 * @AUTHOR LiNian
 * @DATE 2019/9/27 18:05
 */
public class AnalysisData {
    public static void main(String[] args) throws Exception {
//        int result=ToolRunner.run(new AnalysisTextTool(),args);
        int result=ToolRunner.run(new AnalysisBeanTool(),args);
    }
}
