package cn.clickwise.liqi.mapreduce.app.bkw_analysis;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class CLKECSaveTitleMR {

	private static class PrepareMapper extends Mapper<Object, Text, Text, Text> {

		private Text word = new Text();
		private Text word1 = new Text();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {

			String[] seg_arr = (value.toString()).split("\001");

			String cookie = "";
			String access_time = "";
			String host_name = "";
			String title = "";
			String cate_first="";
			String cate_second="";
			String product_url="";
			
			
			if (seg_arr != null && seg_arr.length >= 4) {
				cookie = seg_arr[0].trim();
				access_time = seg_arr[1].trim();
				host_name = seg_arr[2].trim();
				title = seg_arr[3].trim();
				cate_first=seg_arr[4].trim();
				cate_second=seg_arr[5].trim();
				product_url=seg_arr[6].trim();
				
					if (isValidTitle(title)&&(product_url!=null)&&(!product_url.equals(""))) {
						word.set(product_url);
						word1.set("\001"+title);

						double ran = Math.random();
						int rani = -1;
						rani = (int) (100 * ran);
						//if (title.length() > 10) {
							context.write(word, word1);
						//}
					}				
			}

		}

		public boolean isValidTitle(String title) {
			title = title.trim();
			if (title == null || title.equals("")) {
				return false;
			}
			boolean isVal = true;
			String eng_mat = "[a-zA-Z0-9\\.:\\?#=_/&\\-%]*";
			if (Pattern.matches(eng_mat, title)) {
				isVal = false;
			}
			if ((title.indexOf("<") != -1) || (title.indexOf(">") != -1)) {
				isVal = false;
			}

			char first_char = title.charAt(0);
			if (Pattern.matches(eng_mat, first_char + "")) {
				isVal = false;
			}
			if (!isChinese(first_char)) {
				isVal = false;
			}

			return isVal;

		}

		public boolean isChinese(char a) {
			int v = (int) a;
			return (v >= 19968 && v <= 171941);
		}

	}

	private static class PrepareReducer extends Reducer<Text, Text, Text, Text> {
		private Text result = new Text();

		protected void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {

			Iterator<Text> it = values.iterator();

			if (it.hasNext()) {
				context.write(key, it.next());
			}

		}
	}
	

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		if (otherArgs.length != 3) {
			System.err
					.println("Usage: CLKECSaveTitleMR <day> <input> <output>");
			System.exit(2);
		}
		String day = otherArgs[0];
		JobConf jobconf = new JobConf(conf,CLKECSaveTitleMR.class);
		//jobconf.set("key.value.separator.in.input.line", "\001");
		//jobconf.set("mapred.textoutputformat.separator", "\001");
		Job job = new Job(jobconf, "CLKECSaveTitleMR_" + day);
		job.setJarByClass(CLKECSaveTitleMR.class);
		job.setMapperClass(PrepareMapper.class);
		job.setReducerClass(PrepareReducer.class);
		job.setNumReduceTasks(1);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		//mapred.textoutputformat.separator
		//KeyValueTextInputFormat.
		FileInputFormat.addInputPath(job, new Path(otherArgs[1]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}
	
}
