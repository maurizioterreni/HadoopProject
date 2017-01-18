package com.terreni.hadoop.HadoopProject;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.terreni.hadoop.utils.ReadImage;

public class ImagePixelCount {

	public static String INPUT_PATH = "source/input";
	public static String OUTPUT_PATH = "source/output";
	
	public static void main(String[] args) throws Exception{
	
		ReadImage.prepareInput("source/images/image-04.jpg");

		Configuration conf = new Configuration();

		FileSystem fs = FileSystem.get(conf);
		if (fs.exists(new Path(OUTPUT_PATH))) {
            fs.delete(new Path(OUTPUT_PATH), true);
        }
		
		Job job = Job.getInstance(conf, "image batch");

		job.setJarByClass(ImagePixelCount.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setMapperClass(ColorCountMapper.class);
		job.setReducerClass(ColorCountReducer.class);

		//	    if(args.length > 0 && "demo".equals( args[0] )) {
		//	    	FileInputFormat.addInputPath(job, new Path( "/Users/stark/Desktop/hadoop/input/rawdata") );
		//	    	FileOutputFormat.setOutputPath(job, new Path("/Users/stark/Desktop/hadoop/output" ));
		//	    } else {
		//	    	FileInputFormat.addInputPath(job, new Path( "/input/rawdata") );
		//	    	FileOutputFormat.setOutputPath(job, new Path("/output" ));
		//	    }

		FileInputFormat.addInputPath(job, new Path( INPUT_PATH ) );
		FileOutputFormat.setOutputPath(job, new Path( OUTPUT_PATH ));
		job.waitForCompletion(true);

		//    	int i = 0;
		//    	List<String> colors = ReadImage.getImageColor("source/images/image-04.jpg");
		//    	for (String color : colors) {
		//			String colorName = MapColorName.hexToColorName(color);
		//			if(colorName != null){
		//				i++;
		//				System.out.println(i + "  " + colorName + " =>  " + color);
		//			}
		//		}


		//System.out.println(MapColorName.hexToColorName("000000"));
	}
	/*
	public static void main(String[] args) throws IOException {
		for (Map.Entry<String, String> item : MapColorName.getColorMap().entrySet()) {
			System.out.println(item.getValue() + " =>  #" + item.getKey());
		}
	}*/

}
