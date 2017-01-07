package com.terreni.hadoop.HadoopProject;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class ImageWritable implements Writable {

	
	private Text colorName;
	private Text colorCode;
	
	@Override
	public void write(DataOutput out) throws IOException {
		colorName.write(out);
		colorCode.write(out);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		colorName.readFields(in);
		colorCode.readFields(in);
	}

	public Text getColorName() {
		return colorName;
	}

	public void setColorName(Text colorName) {
		this.colorName = colorName;
	}

	public Text getColorCode() {
		return colorCode;
	}

	public void setColorCode(Text colorCode) {
		this.colorCode = colorCode;
	}

	
	@Override
	public int hashCode() {
		return colorCode.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ImageWritable) {
			return ((ImageWritable)obj).getColorName().equals(this.getColorName());
		} 
		return false;
	}
}
