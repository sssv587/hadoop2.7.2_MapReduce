package com.atguigu.mr.friends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author yuhang.sun
 * @date 2020/9/20 - 0:28
 */
public class TwoShareFriendsMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // A I,K,C,B,G,F,H,O,D,
        String line = value.toString();

        //切割
        String[] fields = line.split("\t");

        String commonfriends = fields[0];

        String[] main_person = fields[1].split(",");

        Arrays.sort(main_person);

        for (int i = 0; i < main_person.length - 1; i++) {
            for (int j = i + 1; j < main_person.length; j++) {
                context.write(new Text(main_person[i] + "-" + main_person[j]), new Text(commonfriends));
            }
        }
    }
}
