package com.atguigu.mr.flowsum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author yuhang.sun
 * @date 2020/9/8 - 23:17
 */

public class FlowsumDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();

        //1.获取job对象
        Job job = Job.getInstance(conf);

        //2.设置jar路径
        job.setJarByClass(FlowsumDriver.class);

        //3.关联Mapper和Reducer
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        //4.设置mapper输出的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        //5.设置最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        job.setPartitionerClass(ProvincePartitioner.class);

        job.setNumReduceTasks(5);

        //6.设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path("D:/tmp_study/mapreduce/input/phone_data.txt"));
        FileOutputFormat.setOutputPath(job, new Path("D:/tmp_study/mapreduce/output/phone_data.txt"));

        //7.提交job
        boolean result = job.waitForCompletion(true);

        System.out.println(result ? 0 : 1);
    }
}
