package com.terreni.hadoop.HadoopProject;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class OrderReducer extends Reducer<IntWritable, Text, Text, Text> {
    public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
    	
    	for (Text text : values) {
    	   context.write(text,new Text(key.toString()));
		}
    	
    }
}
