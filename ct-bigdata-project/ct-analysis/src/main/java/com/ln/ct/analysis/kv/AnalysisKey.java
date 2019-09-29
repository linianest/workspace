package com.ln.ct.analysis.kv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Description TODO 自定义分析数据key
 * @AUTHOR LiNian
 * @DATE 2019/9/28 20:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisKey implements WritableComparable<AnalysisKey> {

    private String tel;
    private String date;


    /**
     * 比较数据
     *
     * @param key
     * @return
     */
    @Override
    public int compareTo(AnalysisKey key) {
        int result = tel.compareTo(key.getTel());
        if (result == 0) {
            result = date.compareTo(key.getDate());
        }

        return result;
    }

    /**
     * 写数据
     *
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(tel);
        dataOutput.writeUTF(date);

    }

    /**
     * 读取数据
     *
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        tel = dataInput.readUTF();
        date = dataInput.readUTF();
    }


}
