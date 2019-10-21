package com.ln.sparkstream

import org.apache.spark.sql.SparkSession

object StructuredStreamingNC {

  def main(args: Array[String]): Unit = {
    val spark=SparkSession
      .builder()
      .master("local[2]")
      .appName(this.getClass.getName)
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    val lines = spark.readStream
      .format("socket")
      .option("host", "mini3")
      .option("port", 9999)
      .load()

    import spark.implicits._
    // Split the lines into words
    val words = lines.as[String].flatMap(_.split(" "))

    // Generate running word count
    val wordCounts = words.groupBy("value").count()

    println(wordCounts)
  }


}
