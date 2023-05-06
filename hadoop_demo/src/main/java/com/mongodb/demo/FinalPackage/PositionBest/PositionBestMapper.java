package com.mongodb.demo.FinalPackage.PositionBest;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 输入数据类
 * KEYIN： 默认情况下，输入的KEY数据类型是待处理数据中的某一行内容的起始偏移量。
 * 			类型为Long，hadoop提供了自己的序列化框架，LongWritable代替Long类型
 * VALUEIN：   默认情况下，输入的VALUE的值是某一行数据，类型是String，这里应该使用Text
 *
 * 输出数据类型
 * KEYOUT： map方法处理完成后，要返回的KEY的数据类型
 * VALUEOUT： map方法处理完成后，要返回的VALUE的数据类型
 *
 * 输入的数据格式：
 * good morning
 * good afternoon
 *
 * 输出的数据格式：
 * good 1
 * morning 1
 * good 1
 * afternoon 1
 *
 * @author BLU
 */

public class PositionBestMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

    private Text text = new Text();
    private IntWritable iw = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        //获取一行内容
//        String content = value.toString();//good morning
//        String[] vals = content.split(" ");
//        for(String v : vals) {
//            text.set(v);
//            context.write(text, iw);
//        }


        String line = value.toString(); //取得行数据

        String[] vals = line.split(",");

        Text resultKey = new Text(vals[7]);

        try{
            if(vals[8].equals("N/A")) return;

            IntWritable resultValue = new IntWritable(Integer.valueOf(vals[8]));

            context.write(resultKey, resultValue);

        }catch (Exception e){}

    }
}

