package com.atguigu.mr.order;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.Base64;

/**
 * @author yuhang.sun
 * @date 2020/9/13 - 19:27
 */
public class ProvincePartitioner extends Partitioner<OrderBean, NullWritable> {
    int partition = 4;

    @Override
    public int getPartition(OrderBean bean, NullWritable nullWritable, int numPartitions) {
        if (1 == bean.getOrder_id()) {
            partition = 0;
        } else if (2 == bean.getOrder_id()) {
            partition = 1;
        } else if (3 == bean.getOrder_id()) {
            partition = 2;
        }
        return partition;
    }
}
