package com.spark.test

import kafka.serializer.StringDecoder
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


object StreamingKafka8 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder
      .master("local[2]")
      .appName("streaming")
      .getOrCreate();

    val ssc = new StreamingContext(spark.sparkContext,Seconds(5));

    // Create direct kafka stream with brokers and topics

    val topicsSet = Set("worldcup")
    val kafkaParams = Map[String, String]("metadata.broker.list" -> "hp1:9092")
//    val kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
//      ssc, kafkaParams, topicsSet)
//
//    // Get the lines, split them into words, count the words and print
//    val lines = kafkaStream.map(x=>x._2)
//    val words = lines.flatMap(_.split(" "))
//    val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
//    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
