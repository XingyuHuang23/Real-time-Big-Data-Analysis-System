package com.mongodb.demo.SecondarySort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SecondaryMapper extends Mapper<LongWritable, Text, CustomKey, StockVO> {
    @Override
    protected void map(LongWritable key, Text value,
                       Mapper<LongWritable, Text, CustomKey, StockVO>.Context context) throws IOException, InterruptedException {


        String line = value.toString(); //取得行数据

        if (line.contains("exchange")) {
            return;
        }

        String[] vals = line.split(",");


        StockVO stock = new StockVO();
        stock.setName(vals[1]);
        stock.setDate(vals[2]);
        stock.setVolume(Integer.parseInt(vals[7]));
        stock.setClosePrice(Float.parseFloat(vals[8]));

        CustomKey customKey = new CustomKey();
        customKey.setName(stock.getName());
        customKey.setVolume(stock.getVolume());
        customKey.setClosePrice(stock.getClosePrice());

        context.write(customKey, stock);

    }

}
