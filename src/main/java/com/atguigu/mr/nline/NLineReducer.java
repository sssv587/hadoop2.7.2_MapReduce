package com.atguigu.mr.nline;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author yuhang.sun
 * @date 2020/9/12 - 13:01
 */
public class NLineReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    int sum = 0;
    IntWritable v = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        //1.累加求和
        for (IntWritable value : values
        ) {
            sum += value.get();
        }
        v.set(sum);

        //2.写出
        context.write(key, v);
    }
}
