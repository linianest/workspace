package com.ln.sparkstream

import java.sql.DriverManager

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 将实时处理数据存储到mysql数据库
 */
object MysqlStreaming {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[3]")
      .appName(this.getClass.getName)
      .getOrCreate()

    val sc = spark.sparkContext
    sc.setLogLevel("WARN")

    val ssc = new StreamingContext(sc, Seconds(5))

    val lines = ssc.socketTextStream("mini1", 9999)

    val words = lines.flatMap(_.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)


    words.foreachRDD(rdd => rdd.foreachPartition(lines => {
      Class.forName("com.mysql.jdbc.Driver")
      val url="jdbc:mysql://mini1:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false"
      val conn = DriverManager.getConnection(url, "root","123456")

      try {
        for (row <- lines) {
          val sql="insert into webcount (titlename,count) values ('"+row._1+"',"+row._2+")"
          conn.prepareStatement(sql).executeUpdate()
        }
      }finally {
        conn.close()
      }

    }))
    words.print()
    ssc.start()
    ssc.awaitTermination()


  }


}
