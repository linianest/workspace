package com.ln.udtf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/7 23:55
 */
public class EventJsonUDTF extends GenericUDTF {

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        List<String> filedNames = new ArrayList<>();
        List<ObjectInspector> filedType = new ArrayList<>();
        filedNames.add("event_name");
        filedType.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        filedNames.add("event_json");
        filedType.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(filedNames, filedType);
    }

    @Override
    public void process(Object[] objects) throws HiveException {
        // 获取输入数据
        String input = objects[0].toString();

        if (StringUtils.isBlank(input)) {
            return;
        } else {

            try {
                JSONArray ja = new JSONArray(input);

                if (ja == null) {
                    return;
                }

                for (int i = 0; i < ja.length(); i++) {
                    String[] results = new String[2];

                    try {
                        results[0] = ja.getJSONObject(i).getString("en");
                        results[1] = ja.getString(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        continue;
                    }

                    forward(results);
                }

            } catch (JSONException e) {
                e.printStackTrace();

            }

        }
    }

    @Override
    public void close() throws HiveException {

    }
}
