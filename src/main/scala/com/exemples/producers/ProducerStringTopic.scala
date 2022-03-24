package com.examples.producers

import com.examples.configs._

object ProducerStringTopic extends App with SparkInjector {
  import spark.implicits._

  spark.sparkContext.setLogLevel("WARN")

  var counter = 0
  while(true) {
    Seq((f"Hello world! counter=$counter")).toDF("value")
      .write
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("topic", "test")
      .save()

    counter += 1
    
    Thread.sleep(5000L)
  }
}
