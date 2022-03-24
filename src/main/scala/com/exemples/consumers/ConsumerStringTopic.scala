package com.exemples.consumers

import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.StringType
import com.exemples.configs._

object ConsumerStringTopic extends App with SparkInjector {
  spark.sparkContext.setLogLevel("WARN")

  spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "test")
    .load()

    .withColumn("value", col("value") cast StringType)
  
    .writeStream
    .format("console")
    .outputMode(OutputMode.Append())
    .option("numRows", value=20)
    .option("truncate", value=false)
    .start()
    .awaitTermination()
}
