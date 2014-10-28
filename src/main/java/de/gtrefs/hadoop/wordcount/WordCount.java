package de.gtrefs.hadoop.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCount extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		final Configuration conf = getConf();
		final Job job = Job.getInstance(conf, "wordcount");
		
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setJarByClass(WordCount.class);
		job.setNumReduceTasks(1);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		final boolean result = job.waitForCompletion(true);
		return result ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception {
		int returnCode = ToolRunner.run(new WordCount(), args);
		System.exit(returnCode);
	}
}
