package com.atguigu.mr.order;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author yuhang.sun
 * @date 2020/9/13 - 15:44
 */
public class OrderReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable> {

    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        for (NullWritable nullwritable :
                values) {
                context.write(key, NullWritable.get());
        }
    }
}
