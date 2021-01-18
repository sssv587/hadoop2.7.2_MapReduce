package com.atguigu.mr.kv;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author yuhang.sun
 * @date 2020/9/11 - 23:11
 */

//banzhang ni hao    <banzhang,1>
public class KVTextMapper extends Mapper<Text, Text, Text, IntWritable> {

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        //1.封装对象
        IntWritable v = new IntWritable(1);
        //2.写出
        context.write(key, v);
    }
}
