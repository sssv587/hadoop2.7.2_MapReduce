package com.atguigu.mr.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author yuhang.sun
 * @date 2020/9/8 - 23:09
 */
public class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    FlowBean v = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long sum_upFlow = 0;
        long sum_downFlow = 0;

        //1.累加求和
        for (FlowBean flowbean :
                values) {
            sum_upFlow += flowbean.getUpFlow();
            sum_downFlow += flowbean.getDownFlow();
        }
        v.set(sum_upFlow, sum_downFlow);

        //2.写出
        context.write(key, v);
    }
}
