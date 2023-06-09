package com.mongodb.demo.SequenceFileInput;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
//用之前SequenceFile类型的文件作为处理数据，用那个for循环生成的数据，那个数据指定的类型是<LongWritable,Text>
//SequenceFileInputFormat只能处理SequenceFile类型的数据
public class SequenceFileInputFormatTest {
    public static class MyMapper extends
            Mapper<LongWritable, Text, Text, LongWritable> {
        final Text k2 = new Text();
        final LongWritable v2 = new LongWritable();

        protected void map(LongWritable key, Text value,
                           Mapper<LongWritable, Text, Text, LongWritable>.Context context)
                throws InterruptedException, IOException {
            final String line = value.toString();
            final String[] splited = line.split("\\s");
            for (String word : splited) {
                k2.set(word);
                v2.set(1);
                context.write(k2, v2);
            }
        }
    }

    public static class MyReducer extends
            Reducer<Text, LongWritable, Text, LongWritable> {
        LongWritable v3 = new LongWritable();

        protected void reduce(Text k2, Iterable<LongWritable> v2s,
                              Reducer<Text, LongWritable, Text, LongWritable>.Context context)
                throws IOException, InterruptedException {
            long count = 0L;
            for (LongWritable v2 : v2s) {
                count += v2.get();
            }
            v3.set(count);
            context.write(k2, v3);
        }
    }

    public static void main(String[] args) throws Exception {
        final Configuration conf = new Configuration();
        final Job job = Job.getInstance(conf, SequenceFileInputFormatTest.class.getSimpleName());
        // 1.1
        FileInputFormat.setInputPaths(job,
                "/sf1");

        //set the TextInputFormat to SequenceFileInputFormat
        job.setInputFormatClass(SequenceFileInputFormat.class);


        // 1.2
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        // 1.3 默认只有一个分区
        job.setPartitionerClass(HashPartitioner.class);
        job.setNumReduceTasks(1);
        // 1.4省略不写
        // 1.5省略不写

        // 2.2
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        // 2.3
        FileOutputFormat.setOutputPath(job, new Path(
                "/user/data/output/31"));
        job.setOutputFormatClass(TextOutputFormat.class);
        // 执行打成jar包的程序时，必须调用下面的方法
        job.setJarByClass(SequenceFileInputFormatTest.class);
        job.waitForCompletion(true);
    }
}