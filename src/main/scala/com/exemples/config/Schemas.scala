package com.examples.configs

import org.apache.spark.sql.types.{ArrayType,StringType,DoubleType,StructType,StructField}

object Schemas {
  val product = StructType(Array(
    StructField("id", StringType, nullable=true),
    StructField("name", StringType, nullable=true),
    StructField("price", DoubleType, nullable=true),
  ))

  val products = ArrayType(product, false)
}
