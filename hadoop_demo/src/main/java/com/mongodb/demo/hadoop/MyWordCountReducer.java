package com.mongodb.demo.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 输入的数据类型：mapper输出的数据类型
 * KEYIN,VALUEIN
 * 输出的数据类型：最终输出的数据类型
 * KEYOUT,VALUEOUT
 *
 * 最终输出的数据格式：
 * good 2
 * morning 1
 * afternoon 1
 *
 * @author BLU
 *
 */


public class MyWordCountReducer extends Reducer<Text, FloatWritable, Text, FloatWritable>{

    /**
     * 从mapper来的数据（按字典顺序排序）
     * afternoon 1
     * good 1
     * good 1
     * morning 1
     */

    @Override
    protected void reduce(Text text, Iterable<FloatWritable> value,
                          Reducer<Text, FloatWritable, Text, FloatWritable>.Context context) throws IOException, InterruptedException {

        float max = -1;

        FloatWritable fwmax = new FloatWritable();

        for(FloatWritable fw : value) {
           max = Math.max(fw.get(),max);
        }
        //寻找每个group最大的数据
        fwmax.set(max);

        //System.out.println(max);
        //要输出的数据：
//		afternoon 1
//		good 2
//		morning 1
        context.write(text, fwmax);
    }

}
