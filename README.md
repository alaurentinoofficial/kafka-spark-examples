# Kafka + Spark examples

This repository has 3 examples of how to produce and consume data from Kafka topics based on `string`, `json`, `avro` and `protobuf`.

<br/>

## Requirements

* sbt = 1.6.2 [[site](https://www.scala-sbt.org/download.html)][[SDKMAN](https://sdkman.io/sdks#sbt)]
* JDK 8 [[OpenJDK](https://adoptium.net/)][[SDKMAN](https://sdkman.io/jdks#open)]

<br/>

## Prerequisites

1. Compile Protobuff entities
```sh
$ sbt compile
```

2. Run Kafka locally
```s
$ docker-compose -f docker/docker-compose-kafka.dev.yml up
```

<br/>

## Executing different encoding formats

### 1. String

This example consists of simulating that the content encoded in the topic is a simple text and that, as a result, it will be shown on the consumer's screen.

To execute, you must execute the producer and in another terminal the consumer

* Execute producer
```sh
$ make string-producer
```

* Execute consumer
```sh
$ make string-consumer
```

### 2. Json

This example consists of simulating that the content encoded in the topic is a json, the consumer must serialize the data as a dataframe in Spark.

The schema used represents an Ecommerce product, follows the schema:
* `id`: string
* `name`: string
* `price`: double

To execute, you must execute the producer and in another terminal the consumer

* Execute producer
```sh
$ make json-producer
```

* Execute consumer
```sh
$ make json-consumer
```

### 3. Avro

This example consists of simulating that the content encoded in the topic is a avro, the consumer must serialize the data as a dataframe in Spark.

The schema used represents an Ecommerce product, follows the schema:
* `id`: string
* `name`: string
* `price`: double

The avro file that determines the schema is located at: `./src/main/resources/product.avsc`

To execute, you must execute the producer and in another terminal the consumer

* Execute producer
```sh
$ make avro-producer
```

* Execute consumer
```sh
$ make avro-consumer
```

### 4. Protobuf

This example consists of simulating that the content encoded in the topic is a protobuf, the consumer must serialize the data as a dataframe in Spark. To accomplish that was used the [scalaPB](https://scalapb.github.io/) library for gRPC, for more insights checkout [documentation for SparkSQL](https://scalapb.github.io/docs/sparksql/)

The schema used represents an Ecommerce product, follows the schema:
* `id`: string
* `name`: string
* `price`: double

The protobu can be found at: `./src/main/protobuf/product.proto`

To execute, you must execute the producer and in another terminal the consumer

* Execute producer
```sh
$ make proto-producer
```

* Execute consumer
```sh
$ make proto-consumer
```