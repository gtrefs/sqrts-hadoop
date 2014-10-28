package de.gtrefs.hadoop.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		for(String word:splitLineIntoWords(value)){
			context.write(new Text(word), new IntWritable(1));
		}
	}

	private String[] splitLineIntoWords(Text value) {
		return value.toString().split("\\s");
	}
}
