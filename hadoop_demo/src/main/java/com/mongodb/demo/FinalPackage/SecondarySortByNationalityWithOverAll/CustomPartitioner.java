package com.mongodb.demo.FinalPackage.SecondarySortByNationalityWithOverAll;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import static java.util.Objects.hash;

public class CustomPartitioner extends Partitioner<CustomKey, NullWritable> {

    @Override
    public int getPartition(CustomKey key, NullWritable value, int numPartitions) {
        // hash to perform,same NationalityName will go same reducer.
        return hash(key.getNationality())  % numPartitions;
    }
}