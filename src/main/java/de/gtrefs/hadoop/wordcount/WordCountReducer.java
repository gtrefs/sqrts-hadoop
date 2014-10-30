package de.gtrefs.hadoop.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
	protected void reduce(Text key, Iterable<IntWritable> aggregatedValues, Context context) throws IOException, InterruptedException {
		context.write(key, new IntWritable(sumCounts(aggregatedValues)));
	}

	private int sumCounts(Iterable<IntWritable> aggregatedValues) {
		int totalCount = 0;
		for (IntWritable interMediateValue : aggregatedValues) {
			totalCount += interMediateValue.get();
		}
		return totalCount;
	}
}
