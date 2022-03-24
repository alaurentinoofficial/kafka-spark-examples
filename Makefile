.PHONY: package
package:
	sbt package

.PHONY: assembly
assembly:
	sbt assembly

.PHONY: proto-producer
proto-producer:
	sbt "runMain com.examples.producers.ProducerProtobufTopic"

.PHONY: proto-consumer
proto-consumer:
	sbt "runMain com.examples.consumers.ConsumerProtobufTopic"

.PHONY: avro-producer
avro-producer:
	sbt "runMain com.examples.producers.ProducerAvroTopic"

.PHONY: avro-consumer
avro-consumer:
	sbt "runMain com.examples.consumers.ConsumerAvroTopic"

.PHONY: json-producer
json-producer:
	sbt "runMain com.examples.producers.ProducerJsonStringTopic"

.PHONY: json-consumer
json-consumer:
	sbt "runMain com.examples.consumers.ConsumerJsonStringTopic"

.PHONY: string-producer
string-producer:
	sbt "runMain com.examples.producers.ProducerStringTopic"

.PHONY: string-consumer
string-consumer:
	sbt "runMain com.examples.consumers.ConsumerStringTopic"