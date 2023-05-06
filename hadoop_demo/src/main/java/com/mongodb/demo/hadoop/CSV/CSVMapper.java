package com.mongodb.demo.hadoop.CSV;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.map.WrappedMapper;

import java.io.IOException;

public class CSVMapper extends Mapper<LongWritable, TextArrayWritable, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, TextArrayWritable value, Context context) throws IOException, InterruptedException {

        String[] values = value.toStrings(); //取得行数据
        int sum = 0;

        Text resultKey = new Text(values[0]);

        for (int i = 1; i < values.length; i++) {
            sum = sum + Integer.valueOf(values[i].trim());
        }
        IntWritable resultValue = new IntWritable(sum);
        context.write(resultKey, resultValue);
    }


}
