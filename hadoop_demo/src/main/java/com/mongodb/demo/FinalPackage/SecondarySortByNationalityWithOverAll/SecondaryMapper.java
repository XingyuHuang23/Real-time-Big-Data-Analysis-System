package com.mongodb.demo.FinalPackage.SecondarySortByNationalityWithOverAll;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SecondaryMapper extends Mapper<LongWritable, Text, CustomKey, WorldCupVO> {
    @Override
    protected void map(LongWritable key, Text value,
                       Context context) throws IOException, InterruptedException {


        String line = value.toString(); //取得行数据

        String[] vals = line.split(",");


        WorldCupVO wp = new WorldCupVO();
        wp.setNationality(vals[5]);
        wp.setAge(Integer.parseInt(vals[2]));
        wp.setOverall(Integer.parseInt(vals[6]));
        wp.setName(vals[1]);

        CustomKey customKey = new CustomKey();
        customKey.setNationality(wp.getNationality());
        customKey.setAge(wp.getAge());
        customKey.setOverall(wp.getOverall());
        customKey.setName(wp.getName());

        context.write(customKey, wp);
    }

}
