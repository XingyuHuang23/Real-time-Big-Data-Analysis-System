package com.spark.test.Streaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.ProcessingTime
import org.apache.spark.sql.functions.col

object StructuredStreamingKafka {

  case class WorldCup(uid:String,
                      name:String,
                      age:String,
                      height_cm:String,
                      weight_kg:String,
                      nationality:String,
                      overall:String,
                      team_position:String,
                      pace:String,
                      shooting:String,
                      defending:String,
                      physic:String)

  def main(args: Array[String]): Unit = {

    //init spark session
    val spark = SparkSession
      .builder
      .master("local[2]")
      .appName("streaming")
      .getOrCreate();

    //connect to kafka
    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "hp1:9092")
      .option("subscribe", "worldcup")
      .load()

      import spark.implicits._
      val lines = df.selectExpr("CAST(value AS STRING)").as[String]

     //mapReduce part
      val worldcupMap = lines.map(_.split(",")).map(x=>WorldCup(x(0),x(1),x(2),x(3),x(4),x(5),x(6),x(7),x(8),x(9),x(10),x(11)))

      val worldcupReducer = worldcupMap.groupBy("nationality").count().toDF("nationality","count")
      //.orderBy(col("department").desc) default asc


    //Datebase connection
    val url = "jdbc:mysql://hp1:3306/test"
    val username = "root"
    val pwd = "root"
    val writer = new JDBCSink(url,username,pwd)


    //streaming setting
    val query = worldcupReducer.writeStream
      .foreach(writer)
      .outputMode("update") //3 modes: complete update append
      .trigger(ProcessingTime("5 seconds"))
      .start()

    query.awaitTermination()

  }
}
