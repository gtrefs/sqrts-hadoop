package de.gtrefs.hadoop.sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import de.gtrefs.hadoop.wordcount.WordCount;

public class Sort extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		final Configuration conf = getConf();
		final Job job = Job.getInstance(conf, "sort");

		job.setMapperClass(SortMapper.class);
		job.setReducerClass(SortReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setJarByClass(WordCount.class);
		job.setNumReduceTasks(1);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		final boolean result = job.waitForCompletion(true);
		return result ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		System.exit(ToolRunner.run(new Sort(), args));
	}
}
