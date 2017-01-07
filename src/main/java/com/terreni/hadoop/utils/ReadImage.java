package com.terreni.hadoop.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.terreni.hadoop.HadoopProject.ImagePixelCount;

public class ReadImage {

	private static List<String> getImageColor(String path){
		List<String> colors = new ArrayList<String>();
		try {
			File file= new File(path);
			BufferedImage image;
			image = ImageIO.read(file);
			for(int x = 0; x < image.getWidth(); x++){
				for (int y = 0; y < image.getHeight(); y++) {
					Color clr = new Color(image.getRGB(x, y));
					//System.out.println(Integer.toHexString(clr));
					
					String hex = String.format("%02x%02x%02x", clr.getRed(), clr.getGreen(), clr.getBlue());
					colors.add(hex);
				}
			}

			//System.out.println(Integer.toHexString(clr));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return colors;
	}

	public static void prepareInput(String path){
		List<String> colors = getImageColor(path);
		try{
			PrintWriter writer = new PrintWriter(ImagePixelCount.INPUT_PATH + "/colors.txt", "UTF-8");
			
			int i = 0;
			for (String item : colors) {
				String colorName = MapColorName.hexToColorName(item);
				if(colorName == null){
					colorName = "unknown" + i;
					i++;
				}
				writer.println(colorName + "#" + item);
			}
			writer.close();
		}
		catch (IOException e) {
			// do something
		}
	}

}
