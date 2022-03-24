package com.examples.consumers

import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.functions.{col, from_json, explode}
import org.apache.spark.sql.types.{ArrayType,StringType,DoubleType,StructType,StructField}
import com.examples.configs._
import com.examples.configs.Schemas

object ConsumerJsonStringTopic extends App with SparkInjector {
  spark.sparkContext.setLogLevel("WARN")

  spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "test")
    .load()

    .select(from_json(col("value") cast StringType, Schemas.product) as "data")
    .select("data.*")

    .writeStream
    .format("console")
    .outputMode(OutputMode.Append())
    .option("numRows", value=30)
    .option("truncate", value=false)
    .start()
    .awaitTermination()
}
