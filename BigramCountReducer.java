import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.LinkedList;

public class BigramCountReducer extends Reducer<Text, Text, Text, Text> 
{
    public void reduce(Text key, Iterator<Text> values, Context context) throws IOException, InterruptedException 
	{
		int index = 0;
		Text[] texts = new Text[2];
		while(values.hasNext())
		{
			texts[index++] = new Text(values.next());
		}
		String[] list1 = texts[0].toString().split(" ");
		String[] list2 = texts[1].toString().split(" ");
		List<String> list = new LinkedList<String>();
		for(String friend1 : list1)
		{
			for(String friend2 : list2)
			{
				if(friend1.equals(friend2))
				{
					list.add(friend1);
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < list.size(); i++)
		{
			sb.append(list.get(i));
			if(i != list.size() - 1)
					sb.append(", ");
		}
		context.write(key, new Text(sb.toString()));
    }
}