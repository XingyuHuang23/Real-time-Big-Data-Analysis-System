package com.spark.test

import java.sql.DriverManager

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TestStreaming {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local[2]")
      .appName("hdfsTest")
      .getOrCreate();

    val ssc = new StreamingContext(spark.sparkContext,Seconds(5));
    val lines  = ssc.socketTextStream("hp2",9999);
    val words = lines.flatMap(_.split(" ")).map(word=>(word,1)).reduceByKey(_+_);
    //words.foreachRDD(wd=>wd.saveAsTextFile("hdfs://hp1:9000/user/....")) save res to hdfs
    words.foreachRDD(rdd => rdd.foreachPartition(line =>{
      Class.forName("com.mysql.jdbc.Driver")
      val conn = DriverManager.getConnection("jdbc:mysql://hp1:3306/test","root","root")

      try{
        for(row <- line){
          val sql = "insert into test(title,count)values('"+row._1+"',"+row._2+")"
          conn.prepareStatement(sql).executeUpdate()
        }
      }finally{
        conn.close()
      }
    }))
    words.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
