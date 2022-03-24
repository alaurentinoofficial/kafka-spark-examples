package com.examples.producers

import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.functions.{col, struct, explode}
import org.apache.spark.sql.avro.{to_avro}
import org.apache.spark.sql.types.{ArrayType,StringType,DoubleType,StructType,StructField}
import scala.io.Source
import org.apache.spark.sql.Row
import com.examples.configs._
import com.examples.configs.Schemas

object ProducerAvroTopic extends App with SparkInjector {
  spark.sparkContext.setLogLevel("WARN")

  val data = Seq(
    Row("f55a95e0-51a5-4048-94bd-47ced551901e", "Samsung Galaxy S22 Ultra Smartphone", 1199.99),
    Row("fa94e174-6c53-48a9-8f2f-c490c5dbc04b", "Apple iPhone 13 Pro Max", 1099.0),
  )
  
  val dataDF = spark.createDataFrame(
    spark.sparkContext.parallelize(data),
    Schemas.product
  )
  .withColumn("price", col("price") cast DoubleType)
  .select(to_avro(struct("*")) as "value")

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
