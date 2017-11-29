import java.io.IOException;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.LinkedList;
import java.util.Arrays;

public class BigramCountMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
        StringTokenizer tokenizer = new StringTokenizer(value.toString(), "\n");
				String line = null; //To be read line by line
				String[] arrayOfLines = null; //Put each line in an array
				String[] arrayOfFriends = null; //Put each friend in an array
				String[] helperArray = null; //Helper array for setting keys
				while(tokenizer.hasMoreTokens())
				{
					line = tokenizer.nextToken();
					arrayOfLines = line.split(" ", 2); //First node will be the user
					arrayOfFriends = arrayOfLines[1].split(" "); //Every other node will be friends
					helperArray = new String[2];
					for(int i = 0; i < arrayOfFriends.length; i++)
						{
							helperArray[0] = arrayOfFriends[i]; //Key will essentially be the user themselves
							helperArray[1] = arrayOfLines[0]; //Value will be the friends of the user
							Arrays.sort(helperArray); //Sort the array with Arrays (import java.util.Arrays). This will ensure we will not count nodes twice
							context.write(new Text("{" + helperArray[0] + " " + helperArray[1] + "}"), new Text(arrayOfLines[1])); //Output in a format that is readable
						}
				}
		}
}