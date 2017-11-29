To compile:
export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar 
hadoop com.sun.tools.javac.Main *.java
jar cf bigram.jar *.class

To run:
hadoop jar bigram.jar BigramCount -input /user/username/wordcount/input -numReducers 2 -output /user/username/wordcount/output

To view output:
hadoop fs -cat /user/username/wordcount/output/part-r-00000

When finished, and need to compile/run again:
hadoop fs -rmr /user/username/wordcount/output