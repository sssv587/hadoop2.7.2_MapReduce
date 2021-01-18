package com.atguigu.mr.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author yuhang.sun
 * @date 2020/9/12 - 21:14
 */
public class ProvincePartitioner extends Partitioner<Text, FlowBean> {

    @Override
    public int getPartition(Text key, FlowBean flowBean, int numPartitions) {
        // key是手机号
        // value 流量信息

        // 获取手机号前三位
        String ptrPhoneNum = key.toString().substring(0, 3);
        int partition = 4;

        if ("136".equals(ptrPhoneNum)) {
            partition = 0;
        } else if ("137".equals(ptrPhoneNum)) {
            partition = 1;
        } else if ("138".equals(ptrPhoneNum)) {
            partition = 2;
        } else if ("139".equals(ptrPhoneNum)) {
            partition = 3;
        }


        return partition;
    }
}
