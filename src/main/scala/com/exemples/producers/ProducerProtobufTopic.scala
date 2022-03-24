package com.examples.producers

import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.functions.{col, struct, to_json}
import org.apache.spark.sql.types.{ArrayType,StringType,DoubleType,StructType,StructField}
import scala.io.Source
import org.apache.spark.sql.Row
import com.examples.configs._
import com.examples.configs.Schemas

import com.examples.protos.product.Product
import scalapb.spark.ProtoSQL

import scalapb.spark.Implicits._

object ProducerProtobufTopic extends App with SparkInjector {  
  spark.sparkContext.setLogLevel("WARN")

  val data = Seq(
    Product(
      id="f55a95e0-51a5-4048-94bd-47ced551901e", 
      name="Samsung Galaxy S22 Ultra Smartphone [Proto]", 
      price=1199.99f),
    Product(
      id="fa94e174-6c53-48a9-8f2f-c490c5dbc04b",
      name="Apple iPhone 13 Pro Max [Proto]",
      price=1099.0f),
  )

  val dataDF = spark.createDataset(data.map(_.toByteArray))

  while(true) {
    dataDF
      .write
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("topic", "test")
      .save()
    
    Thread.sleep(5000L)
  }
}
