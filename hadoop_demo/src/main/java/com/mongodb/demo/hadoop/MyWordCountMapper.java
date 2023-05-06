package com.mongodb.demo.hadoop;
import java.io.IOException;

import com.mongodb.demo.hadoop.CSV.TextArrayWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
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

public class MyWordCountMapper extends Mapper<LongWritable, Text, Text, FloatWritable>{

    private Text text = new Text();
    private IntWritable iw = new IntWritable(1);

    //private FloatWritable fw = new FloatWritable(1);

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

        if (line.contains("exchange")) {
            return;
        }
        String[] vals = line.split(",");

        Text resultKey = new Text(vals[1]);

        try{

            FloatWritable resultValue = new FloatWritable(Float.parseFloat(vals[4]));
            context.write(resultKey, resultValue);

            //System.out.println(vals[1]+" : "+vals[4]);

        }catch (Exception e){}

    }
}

