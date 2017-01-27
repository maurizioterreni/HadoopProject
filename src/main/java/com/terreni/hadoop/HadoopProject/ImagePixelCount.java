package com.terreni.hadoop.HadoopProject;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ImagePixelCount {

	public static String COLOR_IMAGE_PATH = "source/imageColor";
	public static String ORDER_COUNT_PATH = "source/OutOrder";
	public static String INPUT_PATH = "source/input";
	public static String OUTPUT_PATH = "source/output";

	public static void main(String[] args) throws Exception{

		//ReadImage.prepareInput("source/images/image-07.jpg");

		Configuration conf = new Configuration();

		FileSystem fs = FileSystem.get(conf);
		if (fs.exists(new Path(OUTPUT_PATH))) {
            fs.delete(new Path(OUTPUT_PATH), true);
        }
		if (fs.exists(new Path(COLOR_IMAGE_PATH))){
			 fs.delete(new Path(COLOR_IMAGE_PATH), true);
		}
		if (fs.exists(new Path(ORDER_COUNT_PATH))){
			 fs.delete(new Path(ORDER_COUNT_PATH), true);
		}

		Job job = Job.getInstance(conf, "image batch");

		job.setJarByClass(ImagePixelCount.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setMapperClass(ColorCountMapper.class);
		job.setReducerClass(ColorCountReducer.class);

		FileInputFormat.addInputPath(job, new Path( INPUT_PATH ) );
		FileOutputFormat.setOutputPath(job, new Path( ORDER_COUNT_PATH ));
		job.waitForCompletion(true);
		
		
		
		
		
		
		
		
		//Map/Reduce Ordinamento risultati
		

	}

}
