package tn.prediction;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;

import java.util.HashMap;
import java.util.Map;

public class Job {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Prediction Job");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, new Duration(2000));
//        JavaSparkContext sc = new JavaSparkContext(conf);

        Map<String, Integer> topicMap = new HashMap<>();
        topicMap.put("request-prediction", 1);
        topicMap.put("receive-prediction", 2);

        JavaPairReceiverInputDStream<String, String> messages =
                KafkaUtils.createStream(jssc, "localhost:2181", "group_id", topicMap);

        JavaDStream<String> jsonObject = messages.map(Tuple2::_2);

        // create a Dataframe from the spark sql library
        // containing features -> table of the values contained in the json object

        // creating a java spark context to load the model from HDFS

        // loading the model

        //predicting the outcome

        // creating a json object from the dataframe resulting from the prediction

        // pushing the jason object to the second topic
        jsonObject.print();fis
    }
}
