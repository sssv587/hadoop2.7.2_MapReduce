package com.atguigu.mr.sort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeMap;

/**
 * @author yuhang.sun
 * @date 2020/9/13 - 0:44
 */
public class FlowCountSortReducer extends Reducer<FlowBean, Text, Text, FlowBean> {
    // 定义一个TreeMap作为存储数据的容器（天然按key排序）
    TreeMap<FlowBean, Text> flowMap = new TreeMap<FlowBean, Text>();
    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value :
                values) {
            context.write(value, key);
        }
    }
}
