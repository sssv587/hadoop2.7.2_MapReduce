package com.atguigu.mr.order;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author yuhang.sun
 * @date 2020/9/13 - 15:39
 */
public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {
    OrderBean k = new OrderBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //0000001	Pdt_01	222.8

        //1.获取一行
        String line = value.toString();

        //2.切割
        String[] fields = line.split(" ");

        //3.封装对象
        k.setOrder_id(Integer.parseInt(fields[0]));
        k.setPrice(Double.parseDouble(fields[2]));

        //4.写出
        context.write(k, NullWritable.get());
    }
}
