package com.terreni.hadoop.HadoopProject;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OrderMapper extends Mapper<LongWritable, Text , Text, Text> {
 
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] temp = value.toString().split("\t");
    	
    	context.write(new Text(temp[1]),new Text(temp[0]));
    	
    	//System.out.println("#####################################"  + temp[0]  + "-" + temp[1]  + "#####################################");
    }
}