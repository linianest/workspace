package com.ln.sparkstream

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Kafka10Streaming {
  def main(args: Array[String]): Unit = {
    case class Weblogs(datetime: String,
                       userid: String,
                       searchname: String,
                       retorder: String,
                       cliorder: String,
                       cliurl: String)

    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName(this.getClass.getName)
      .getOrCreate()

    val sc = spark.sparkContext
    val ssc = new StreamingContext(sc, Seconds(5))


    val kafkaParams = Map[String, Object](
      "bootstreap.servers" -> "mini1:9092,mini2:9092,mini3:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "weblogs",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("weblogs")
    val stream = KafkaUtils.createDirectStream[String,String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    val lines=stream.map(x=>x.value())

//    val weblog = lines.map(_.split(",")).map(x =>
//      Weblogs(x(0), x(1), x(2), x(3), x(4), x(5))
//    )
//    val titleCount = weblog.groupBy("searchname").count().toDF("titleName", "count")
//    val wordsCount=words.map(x=>(x,1L)).reduceByKey(_+_)

    lines.print()

    ssc.start()
    ssc.awaitTermination()


  }

}
