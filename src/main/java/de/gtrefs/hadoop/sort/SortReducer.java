package de.gtrefs.hadoop.sort;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SortReducer extends Reducer<Text, Text, Text, Text> {
	
	@Override
	protected void reduce(Text word, Iterable<Text> empty, Context context) throws IOException, InterruptedException {
		context.write(word, new Text());
	}
}
