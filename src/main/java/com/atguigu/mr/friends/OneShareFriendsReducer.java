package com.atguigu.mr.friends;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author yuhang.sun
 * @date 2020/9/19 - 23:55
 */
public class OneShareFriendsReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //拼接字符串
        StringBuffer sb = new StringBuffer();

        //聚合
        for (Text value : values
        ) {
            sb.append(value.toString() + ",");
        }

        context.write(key, new Text(sb.toString()));
    }
}
