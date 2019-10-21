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
class KafkaSinkProducer(topic: String, servers: String) extends ForeachWriter[(String, String)] {
  val kafkaProperties = new Properties()

  kafkaProperties.put("bootstreap.servers", servers)
//  kafkaProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
//  kafkaProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  kafkaProperties.put("key.serializer", "kafkashaded.org.apache.kafka.common.serialization.StringSerializer")
  kafkaProperties.put("value.serializer", "kafkashaded.org.apache.kafka.common.serialization.StringSerializer")

  val result = new mutable.HashMap[String, String]
  var producer: KafkaProducer[String, String] = _

  override def open(partitionId: Long, version: Long): Boolean = {
    producer=new KafkaProducer(kafkaProperties)
    true
  }

  override def process(value: (String, String)): Unit = {
    producer.send(new ProducerRecord(topic,value._1+":"+value._2))
  }

  override def close(errorOrNull: Throwable): Unit = {
    if (producer!=null){
      producer.close()
    }
  }
}
