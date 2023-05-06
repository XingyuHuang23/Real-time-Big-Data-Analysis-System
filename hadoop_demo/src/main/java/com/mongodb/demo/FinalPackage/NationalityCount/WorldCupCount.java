package com.mongodb.demo.FinalPackage.NationalityCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WorldCupCount {
    public static void main(String[] args) {
        System.out.println(args[0]+"  "+args[1]);
        try {
            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf);

            //设置运行的类
            job.setJarByClass(WorldCupCount.class);

            //设置mapper和reducer对应的类
            job.setMapperClass(WorldCupMapper.class);
            job.setReducerClass(WorldCupReducer.class);

            //设置combiner
            job.setCombinerClass(WorldCupReducer.class);

            //运行mapper的输出数据类型和最终输出的数据类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            //指定划分切片的行数
           NLineInputFormat.setNumLinesPerSplit(job, 2);
           //指定InputFormat的类型
           job.setInputFormatClass(NLineInputFormat.class);

            //设置文件的输入和输出的路径
            // hadoop jar example.jar wordcount /input/a.txt /output
            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            //运行job
            boolean flag = job.waitForCompletion(true);
            //0表示正常退出
            //1表示不正常退出
            System.exit(flag ?0 : 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
