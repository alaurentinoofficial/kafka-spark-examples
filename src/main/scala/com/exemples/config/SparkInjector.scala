package com.examples.configs

import org.apache.spark.sql.SparkSession

trait SparkInjector {
  lazy val spark = SparkSession
    .builder
    .appName("Kafka + Spark")
    .master("local[*]")
    .getOrCreate()
}