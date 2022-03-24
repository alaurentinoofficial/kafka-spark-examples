package com.exemples.consumers

import org.apache.spark.sql.streaming.OutputMode
import scala.io.Source
import com.exemples.configs._

import com.examples.protos.product.Product
import scalapb.spark.ProtoSQL

import scalapb.spark.Implicits._

object ConsumerProtobufTopic extends App with SparkInjector {
  spark.sparkContext.setLogLevel("WARN")
  
  spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "test")
    .load()

    .selectExpr("value").as[Array[Byte]]
    .map(Product.parseFrom(_))

    .writeStream
    .format("console")
    .outputMode(OutputMode.Append())
    .option("numRows", value=30)
    .option("truncate", value=false)
    .start()
    .awaitTermination()
}
