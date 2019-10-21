package com.ln.sparkstream


import com.ln.sparkstream.util.JDBCSink
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.ProcessingTime
import org.apache.spark.streaming.{Seconds, StreamingContext}

case class Weblogs(datetime: String,
                   userid: String,
                   searchname: String,
                   retorder: String,
                   cliorder: String,
                   cliurl: String)

object StructuredStramingKafka {
  def main(args: Array[String]): Unit = {


    val spark = SparkSession
      .builder()
      .appName(this.getClass.getName)
      .master("local[2]")
      .getOrCreate()

//    val sc=spark.sparkContext
    spark.sparkContext.setLogLevel("WARN")
//
//    val ssc = new StreamingContext(sc, Seconds(2))
    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers","mini1:9092,mini2:9092,mini3:9092")
      .option("subscribe", "weblogs")
      .load()


//    val zookeeperServer="mini1:2181"//zookeeper服务器地址(默认的)
//    val group="weblogs"
//    val topics="weblogs"
//    val num=1  //每个topic的分区数
//    val topicMap=topics.split(",").map((_,num.toInt)).toMap
//    val df= {
////      createDirectStream[K, V](ssc : org.apache.spark.streaming.StreamingContext, locationStrategy : org.apache.spark.streaming.kafka010.LocationStrategy, consumerStrategy : org.apache.spark.streaming.kafka010.ConsumerStrategy[K, V]) : org.apache.spark.streaming.dstream.InputDStream[org.apache.kafka.clients.consumer.ConsumerRecord[K, V]] =
//
////      KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, zookeeperServer, group, topicMap)
//    }

//    val df=spark.readStream
//      .format("kafka")
//      .option("kafka.bootstrap.servers", "mini1:9092,mini2:9092,mini3:9092")
//      .option("subscribe", "weblogs")
//      .load()

    // 解析weblogs数据
    import spark.implicits._
    val lines = df.selectExpr("CAST(value AS STRING)").as[String]
    val weblog = lines.map(_.split(",")).map(x =>
      Weblogs(x(0), x(1), x(2), x(3), x(4), x(5))
    )
    // 计算count
    val titleCount = weblog.groupBy("searchname").count().toDF("titleName", "count")

    val url = "jdbc:mysql://mini1:3306/test"
    val username = "root"
    val password = "123456"

    val writer = new JDBCSink(url, username, password)
    val query = titleCount.writeStream
      .foreach(writer)
      .outputMode("update")
      .format("console")
      .trigger(ProcessingTime("5 seconds"))
      .start()

    query.awaitTermination()


  }


}
