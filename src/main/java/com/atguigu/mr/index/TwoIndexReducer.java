package com.atguigu.mr.index;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author yuhang.sun
 * @date 2020/9/19 - 22:23
 */
public class TwoIndexReducer extends Reducer<Text, Text, Text, Text> {
    Text v = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //atguigu  a.txt	3
        //atguigu  b.txt	2
        //atguigu c.txt-->2  b.txt-->2  c.txt-->2

        //1.拼接字符串
        StringBuffer sb = new StringBuffer();

        for (Text value :
                values) {
            sb.append(value.toString().replace("\t", "-->")).append("\t");
        }
        v.set(sb.toString());

        //2.写出
        context.write(key, v);
    }
}
