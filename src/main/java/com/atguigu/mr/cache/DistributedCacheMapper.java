package com.atguigu.mr.cache;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

/**
 * @author yuhang.sun
 * @date 2020/9/13 - 22:18
 */
public class DistributedCacheMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    HashMap<String, String> pdMap = new HashMap<>();
    Text k = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 缓存小表
        URI[] cacheFiles = context.getCacheFiles();
        String path = cacheFiles[0].getPath().toString();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

        String line;
        while (StringUtils.isNotEmpty(line = reader.readLine())) {
            // 1 切割
            String[] fields = line.split("\t");

            pdMap.put(fields[0], fields[1]);
        }

        //关闭资源
        IOUtils.closeStream(reader);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.huoquyihang
        String line = value.toString();

        //2.切割
        String[] fields = line.split("\t");

        //3.获取pid
        String pid = fields[1];

        //4.取出pname
        String pname = pdMap.get(pid);

        //5.拼接
        line = line + "\t" + pname;

        k.set(line);
        //6.写出
        context.write(k, NullWritable.get());
    }
}
