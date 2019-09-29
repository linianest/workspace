package com.ln.ct.analysis.kv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Description TODO 自定义分析数据value
 * @AUTHOR LiNian
 * @DATE 2019/9/28 20:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisValue implements Writable {

    private String sumCall;
    private String sumDuration;

    /**
     * 写数据
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(sumCall);
        dataOutput.writeUTF(sumDuration);
    }

    /**
     * 读数据
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        sumCall=dataInput.readUTF();
        sumDuration=dataInput.readUTF();
    }
}
