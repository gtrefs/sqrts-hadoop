package de.gtrefs.hadoop.sort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		for (String word : value.toString().split("\\s")) {
			context.write(new Text(word.toLowerCase()), new Text());
		}
	}
}
