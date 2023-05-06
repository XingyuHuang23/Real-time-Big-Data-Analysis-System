package com.mongodb.demo.SecondarySort;


import com.mongodb.demo.hadoop.MyWordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ToolRunner;

public class RunSecondarySort  {


    public static void main(String[] args) {
        System.out.println(args[0]+"  "+args[1]);
        if (args.length != 2) {
            System.err.printf("args error!");
            ToolRunner.printGenericCommandUsage(System.err);
            return;
        }
        try {
            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf);
            job.setJobName("Stock_ClosePrice");

            //设置运行的类
            job.setJarByClass(RunSecondarySort.class);

            //设置mapper和reducer对应的类
            job.setMapperClass(SecondaryMapper.class);
            job.setReducerClass(SecondaryReducer.class);

            //set combiner
            job.setCombinerClass(SecondaryReducer.class);

            //设置自定义keyComparator
            job.setPartitionerClass(CustomPartitioner.class);
            job.setSortComparatorClass(KeyComparator.class);
            job.setGroupingComparatorClass(GroupComparator.class);

            //运行mapper的输出数据类型和最终输出的数据类型
            job.setMapOutputKeyClass(CustomKey.class);
            job.setMapOutputValueClass(StockVO.class);

            job.setOutputKeyClass(NullWritable.class);
            job.setOutputValueClass(Text.class);

            //设置文件输入类型
            //job.setInputFormatClass(CSVInputFormat.class);

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
