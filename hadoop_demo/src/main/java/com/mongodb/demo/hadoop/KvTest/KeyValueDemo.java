package com.mongodb.demo.hadoop.KvTest;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class KeyValueDemo {

    public static void main(String[] args) throws Exception {

        Job job = Job.getInstance();

        Configuration conf = new Configuration();

        //"tab" as the divider
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, "\t");

        job.setInputFormatClass(KeyValueTextInputFormat.class);

        job.setJarByClass(KeyValueDemo.class);

        job.setMapperClass(Kvmapper.class);
        job.setReducerClass(KvReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);
    }
}
