package com.suning.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author lynn
 * @Date 2020/9/3 20:23
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1. 拿到job实例
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        //2. 设置3个类
        job.setJarByClass(WordCountDriver.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //3. 设置4个输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //4. 设置输入路径和输出路径C:\Java\IdeaProjects\Bigdata_Review\Test\src\data\input.txt
        FileInputFormat.setInputPaths(job,new Path("C:\\Java\\IdeaProjects\\Bigdata_Review\\Test\\src\\data\\input.txt"));
        FileOutputFormat.setOutputPath(job,new Path("C:\\Java\\IdeaProjects\\Bigdata_Review\\Test\\src\\data\\output.txt"));

        //5. 程序执行
        job.submit();
    }
}
