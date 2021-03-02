package upf.edu;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import upf.edu.parser.SimplifiedTweet;

import java.util.Arrays;

public class TwitterLanguageFilterApp {

    public static void main(String[] args){
    	// If we pass a directory Spark processes it.
    	String language = args[0];
        String outputDir = args[1];
        String input = args[2];
        
        //Create a SparkContext to initialize
        SparkConf conf = new SparkConf().setAppName("Twitter Language Filter App");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        
        // Load input
        JavaRDD<String> sentences = sparkContext.textFile(input);

        JavaRDD<String> filteredTweets = sentences
            .flatMap(s -> Arrays.asList(s.split("[\n]")).iterator())
            .map(word -> SimplifiedTweet.fromJson(word))
            .filter(optional -> optional.isPresent())
            .filter(tweet -> tweet.get().getLanguage().equals(language))
            .map(simplifiedTweet -> simplifiedTweet.get().toString());				//RDD<String>
        
        System.out.println("Number of Tweets: " + filteredTweets.count());
        filteredTweets.saveAsTextFile(outputDir);
        sparkContext.close();
    }

   /* private static String normalise(String word) {
        return word.trim().toLowerCase();
    }*/
}
