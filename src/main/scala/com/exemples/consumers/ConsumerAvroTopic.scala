package com.exemples.consumers

import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.functions.{col, explode}
import org.apache.spark.sql.avro.{from_avro}
import org.apache.spark.sql.types.{ArrayType,StringType,DoubleType,StructType,StructField}
import scala.io.Source
import com.exemples.configs._

object ConsumerAvroTopic extends App with SparkInjector {
  spark.sparkContext.setLogLevel("WARN")

  val avroSchema = Source.fromResource("product.avsc").getLines.mkString
  
  spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "test")
    .load()

    .select(from_avro(col("value"), avroSchema) as ("data"))
    .select("data.*")

    .writeStream
    .format("console")
    .outputMode(OutputMode.Append())
    .option("numRows", value=30)
    .option("truncate", value=false)
    .start()
    .awaitTermination()
}
