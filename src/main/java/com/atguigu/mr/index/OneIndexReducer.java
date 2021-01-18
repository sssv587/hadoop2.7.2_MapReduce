package com.atguigu.mr.index;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author yuhang.sun
 * @date 2020/9/19 - 22:13
 */
public class OneIndexReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        //1.累加求和
        for (IntWritable value :
                values) {
            sum += value.get();
        }

        IntWritable v = new IntWritable();
        v.set(sum);
        //2.写出
        context.write(key, v);

    }
}
