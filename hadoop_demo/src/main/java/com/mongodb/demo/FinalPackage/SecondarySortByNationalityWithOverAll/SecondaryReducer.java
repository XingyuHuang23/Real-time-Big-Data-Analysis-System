package com.mongodb.demo.FinalPackage.SecondarySortByNationalityWithOverAll;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SecondaryReducer extends Reducer<CustomKey, WorldCupVO, NullWritable, Text> {

    NullWritable nullKey = NullWritable.get();
    Text text = new Text();
    @Override
    protected void reduce(CustomKey key, Iterable<WorldCupVO> values,
                          Context context)
            throws IOException, InterruptedException {

//        for(WorldCupVO stock:values){
////            context.write(nullKey, stock);
////        }

        WorldCupVO wp = values.iterator().next();
        text.set(wp.getNationality()  +", "+wp.getName()+", "+wp.getOverall());

        context.write(nullKey,text);
    }
}