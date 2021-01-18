package com.atguigu.mr.friends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author yuhang.sun
 * @date 2020/9/19 - 23:52
 */
public class OneShareFriendsMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //A:B,C,D,F,E,O

        //1.获得一行
        String line = value.toString();

        //2.切割
        String[] fields = line.split(":");

        //3.写出
        String[] friends = fields[1].split(",");

        for (String friend :
                friends) {
            context.write(new Text(friend), new Text(fields[0]));
        }
    }
}
