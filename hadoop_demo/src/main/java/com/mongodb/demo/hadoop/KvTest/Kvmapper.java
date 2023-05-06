package com.mongodb.demo.hadoop.KvTest;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 输出格式：
 * zhangsan 1
 * lisi 1
 * zhangsan 1
 *
 * @author BLU
 *
 */
public class Kvmapper extends Mapper<Text, Text, Text, IntWritable>{

    /**
     * 输入格式：
     * zhangsan 500 450 jan
     * key:zhangsan
     * value:500 450 jan
     */

    private IntWritable iw = new IntWritable(1);

    @Override
    protected void map(Text key, Text value, Mapper<Text, Text, Text, IntWritable>.Context context)
            throws IOException, InterruptedException {

        context.write(key, iw);
    }
}
