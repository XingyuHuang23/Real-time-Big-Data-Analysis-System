package com.mongodb.demo.hadoop.KvTest;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class KvReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

    IntWritable iw = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> value,
                          Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {

        int sum = 0;
        for(IntWritable iw : value) {
            sum += iw.get();
        }
        iw.set(sum);
        context.write(key, iw);
    }

}
