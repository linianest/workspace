package com.ln.sparkstream

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * socket 实时流数据
 */
object StructuredStreamingSocket {

  def main(args: Array[String]): Unit = {
    val spark=SparkSession
      .builder()
      .master("local[2]")
      .appName(this.getClass.getName)
      .getOrCreate()

    val sc=spark.sparkContext
    sc.setLogLevel("WARN")

    val ssc=new StreamingContext(sc,Seconds(5))

    val lines=ssc.socketTextStream("mini3",9999)

    val words=lines.flatMap(_.split(" ")).map(word=>(word,1)).reduceByKey(_+_)


    words.print()

    ssc.start()
    ssc.awaitTermination()


  }

}
