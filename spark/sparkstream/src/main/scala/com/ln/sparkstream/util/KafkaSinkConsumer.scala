package com.ln.sparkstream.util

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.spark.sql.ForeachWriter

import scala.collection.mutable

/**
 * kafka 生产数据
 * @param topic
 * @param servers
 */
class KafkaSinkConsumer(topic: String, servers: String) extends ForeachWriter[(String, String)] {
  val kafkaProperties = new Properties()

  kafkaProperties.put("bootstreap.servers", servers)
  kafkaProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  kafkaProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
//  kafkaProperties.put("group.id", "")
//  kafkaProperties.put("enable.auto.commit", true)
//  kafkaProperties.put("auto.commit.interval.ms", 1000)

  val result = new mutable.HashMap[String, String]
  var consumer: KafkaSinkConsumer = _

  override def open(partitionId: Long, version: Long): Boolean = {
//    consumer=new KafkaSinkConsumer(kafkaProperties)
    true
  }

  override def process(value: (String, String)): Unit = {
//     consumer.
  }

  override def close(errorOrNull: Throwable): Unit = {
    if (consumer!=null){
    }
  }
}
