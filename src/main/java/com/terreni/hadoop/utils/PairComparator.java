package com.terreni.hadoop.utils;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class PairComparator extends WritableComparator {
	protected PairComparator() {
		super(IntWritable.class, true);
	}   
	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		IntWritable i1 = (IntWritable) w1;
		IntWritable i2 = (IntWritable) w2;
		
		if( i1.get() > i2.get()){
			return -1;
		}else if(i1.get() == i2.get()){
			return 0;
		}else{
			return 1;
		}
		//return super.compare(a, b);
	}
	//    public int compare(WritableComparable w1, WritableComparable w2) {
	//        Pair k1 = (Pair)w1;
	//        Pair k2 = (Pair)w2;         
	//        return  k1.getValue().compareTo(k2.getValue());
}