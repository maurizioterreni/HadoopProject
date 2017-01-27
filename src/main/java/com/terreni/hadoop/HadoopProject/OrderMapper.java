package com.terreni.hadoop.HadoopProject;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OrderMapper extends Mapper<LongWritable, Text , IntWritable, Text> {
 
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
    	String token[] = value.toString().split("\t");
    	
    	
    	context.write(new IntWritable(Integer.parseInt(token[1])),new Text(token[0]));
    	
   
    }
}