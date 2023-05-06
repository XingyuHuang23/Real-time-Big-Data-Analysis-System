package com.mongodb.demo.FinalPackage.PositionBest;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

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


public class PositionBestReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

    /**
     * 从mapper来的数据（按字典顺序排序）
     * afternoon 1
     * good 1
     * good 1
     * morning 1
     */

    @Override
    protected void reduce(Text text, Iterable<IntWritable> value,
                          Context context) throws IOException, InterruptedException {

        int max = 0;

        IntWritable iMax = new IntWritable();

        for(IntWritable iw : value) {
            max  = Math.max(iw.get(),max);
        }
        //寻找每个group最大的数据
        iMax.set(max);

        //System.out.println(max);
        //要输出的数据：
//		afternoon 1
//		good 2
//		morning 1
        context.write(text, iMax);
    }

}
