package com.suning.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author lynn
 * @Date 2020/9/6 17:24
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    IntWritable v = new IntWritable();
    int count;
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        count = 0;
        for (IntWritable value : values){
            count+=value.get();
            //count+=1;
        }
        v.set(count);
        context.write(key,v);
    }
}
