package com.mongodb.demo.SecondarySort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SecondaryReducer extends Reducer<CustomKey, StockVO, NullWritable, Text> {

    NullWritable nullKey = NullWritable.get();
    Text text = new Text();
    @Override
    protected void reduce(CustomKey key, Iterable<StockVO> values,
                          Reducer<CustomKey, StockVO, NullWritable, Text>.Context context)
            throws IOException, InterruptedException {

//        for(WorldCupVO stock:values){
////            context.write(nullKey, stock);
////        }

        StockVO stock = values.iterator().next();
        text.set(stock.getName()  +","+stock.getClosePrice());

        context.write(nullKey,text);
    }
}