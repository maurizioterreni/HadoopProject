package com.terreni.hadoop.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapColorName {
	private static String LIST_COLOR_FILE_PATH = "source/ListColor.txt";


	private static String readFile() throws IOException{
		//colorName = new HashMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(LIST_COLOR_FILE_PATH));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public static Map<String,String> getColorMap(){
		Map<String,String> colorMap = new HashMap<String, String>();
		try {
			String str = readFile();
			for (String element : str.split("</tr>")) {
				//System.out.println(element);
				try{
					int start = element.indexOf("\">") + 2;
					int end = element.indexOf("</a>");

					String name = element.substring(start,end);
					
					element = element.substring(end + 10);
					
					start = element.indexOf("\">") + 3;
					end = element.indexOf("</a>");
					
					String code = element.substring(start,end);
					
					//System.out.println(name + "  -> #" + code);
					colorMap.put(code, name);
					//System.out.println(element);
				}catch(Exception e){}
			}
			return colorMap;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return colorMap;
		}
	
	}
}
