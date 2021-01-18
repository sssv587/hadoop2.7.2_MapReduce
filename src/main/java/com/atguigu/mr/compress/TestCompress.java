package com.atguigu.mr.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;

/**
 * @author yuhang.sun
 * @date 2020/9/15 - 0:17
 */
public class TestCompress {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 压缩
//        compress("D:/tmp_study/mapreduce/input/hello.txt", "org.apache.hadoop.io.compress.BZip2Codec");
//        compress("D:/tmp_study/mapreduce/input/hello.txt", "org.apache.hadoop.io.compress.GzipCodec");
//        compress("D:/tmp_study/mapreduce/input/hello.txt", "org.apache.hadoop.io.compress.DefaultCodec");
        decompress("D:/tmp_study/mapreduce/input/hello.txt.deflate");
    }

    private static void decompress(String fileName) throws IOException {
        //1.压缩方式检查
        CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());
        CompressionCodec codec = factory.getCodec(new Path(fileName));

        if (codec == null) {
            System.out.println("can not process");
            return;
        }

        //2.获取输入流
        FileInputStream fis = new FileInputStream(new File(fileName));
        CompressionInputStream cis = codec.createInputStream(fis);

        //3.获取输出流
        FileOutputStream fos = new FileOutputStream(new File(fileName + ".decode"));

        //4.流的对接
        IOUtils.copyBytes(cis, fos, 1024 * 1024, false);

        //5.关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(cis);
        IOUtils.closeStream(fis);
    }

    private static void compress(String fileName, String method) throws IOException, ClassNotFoundException {
        //1.获取输入流
        FileInputStream fis = new FileInputStream(new File(fileName));
        Class classCodec = Class.forName(method);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(classCodec, new Configuration());

        //2.获取输出流
        FileOutputStream fos = new FileOutputStream(new File(fileName + codec.getDefaultExtension()));
        CompressionOutputStream cos = codec.createOutputStream(fos);

        //3.流的对拷
        IOUtils.copyBytes(fis, cos, 1024 * 1024 * 5, false);

        //4.关闭流
        IOUtils.closeStream(cos);
        IOUtils.closeStream(fos);
    }


}
