package com.terreni.hadoop.HadoopProject;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.terreni.hadoop.utils.MapColorName;
import com.terreni.hadoop.utils.ReadImage;

 
public class ColorCountMapper extends Mapper<LongWritable, Text , Text, IntWritable> {
 
    private final IntWritable one = new IntWritable(1);
    
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	
		
    	List<String> colors = ReadImage.getImageColor(value.toString());
    	
    	for (String color : colors) {
    		String colorName = MapColorName.hexToColorName(color , 1);
    		if(colorName != null){
    			context.write(new Text(colorName),one);
			}
    		
		}
    	
 
    	
    	//System.out.println("#####################################"  + value.toString());
    }
}
