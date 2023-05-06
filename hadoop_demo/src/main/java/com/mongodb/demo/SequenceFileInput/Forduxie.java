package com.mongodb.demo.SequenceFileInput;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.zookeeper.common.IOUtils;

//for循环读写操作演示
public class Forduxie {
    public static void main(String args[]) throws Exception {
        final Path path = new Path("/user/data/sf1");
        Configuration conf = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://192.168.40.151/"), conf);

        @SuppressWarnings("deprecation")
        final SequenceFile.Writer writer = new SequenceFile.Writer(fs, conf,path, LongWritable.class,Text.class);
        for (int i = 0; i < 10; i++) {
            writer.append(new LongWritable(i), new Text(i+"=_="));
        }
        IOUtils.closeStream(writer);

        @SuppressWarnings({ "deprecation" })
        final SequenceFile.Reader reader = new SequenceFile.Reader(fs, path,conf);
        LongWritable key = new LongWritable();
        Text val = new Text();
        while (reader.next(key, val)) {
            System.out.println(key.get() + "\t" + val.toString());
        }IOUtils.closeStream(reader);
    }
}
