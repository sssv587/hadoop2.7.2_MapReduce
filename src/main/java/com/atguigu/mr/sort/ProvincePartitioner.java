package com.atguigu.mr.sort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author yuhang.sun
 * @date 2020/9/13 - 0:55
 */
public class ProvincePartitioner extends Partitioner<FlowBean, Text> {
    @Override
    public int getPartition(FlowBean key, Text value, int numPartitions) {
        int partition = 4;

        //按照手机号的前3为分区
        String prephoneNum = value.toString().substring(0, 3);

        if ("136".equals(prephoneNum)) {
            partition = 0;
        } else if ("137".equals(prephoneNum)) {
            partition = 1;
        } else if ("138".equals(prephoneNum)) {
            partition = 2;
        } else if ("139".equals(prephoneNum)) {
            partition = 3;
        }

        return partition;
    }
}
