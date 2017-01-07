package com.terreni.hadoop.HadoopProject;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

 
public class ColorCountMapper extends Mapper<LongWritable, Text , Text, IntWritable> {
 
    private final IntWritable one = new IntWritable(1);
   // private Text word = new Text();
 
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	String[] tokens = value.toString().split("#");
		ImageWritable iw = new ImageWritable();
		iw.setColorCode(new Text(tokens[1]));
		iw.setColorName(new Text(tokens[0]));
		context.write(new Text(tokens[0]),one);
    }
}
