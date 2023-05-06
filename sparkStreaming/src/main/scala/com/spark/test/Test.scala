package com.spark.test

import org.apache.spark.sql.SparkSession

object Test {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local")
      .appName("hdfsTest")
      .getOrCreate();

     // val filePath = "G://access.log";
//      val rdd = spark.sparkContext.textFile(filePath);
//      val lines = rdd.flatMap(x=>x.split("\t")).map(x=>(x,1)).reduceByKey((a,b)=>(a+b)).collect().toList

    val filePath = args(0);
    import spark.implicits._
    val dataset = spark.read.textFile(filePath)
      .flatMap(x => x.split(" "))
      .map(x => (x,1)).groupBy("_1")
      .count()
      .show()

      //print(lines)
  }
}
