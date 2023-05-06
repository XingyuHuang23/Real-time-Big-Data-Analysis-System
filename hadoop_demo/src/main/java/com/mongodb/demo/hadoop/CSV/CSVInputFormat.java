package com.mongodb.demo.hadoop.CSV;


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

public class CSVInputFormat extends FileInputFormat<LongWritable, TextArrayWritable> {
    public static final String CSV_TOKEN_SEPARATOR_CONFIG = "csvinputformat.token.delimiter";

    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        CompressionCodec codec = new CompressionCodecFactory(context.getConfiguration()).getCodec(filename);
        return codec == null;
    }

    @Override
    public RecordReader<LongWritable, TextArrayWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        String csvDelimiter = context.getConfiguration().get(CSV_TOKEN_SEPARATOR_CONFIG);
        Character separator = null;
        if (csvDelimiter != null && csvDelimiter.length() == 1) {
            separator = csvDelimiter.charAt(0);
        }
        return new CSVRecordReader(separator);
    }
}