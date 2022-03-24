.PHONY: package
package:
	sbt package

.PHONY: assembly
assembly:
	sbt assembly

.PHONY: avro-producer
avro-producer:
	sbt "runMain com.exemples.producers.ProducerAvroTopic"

.PHONY: avro-consumer
avro-consumer:
	sbt "runMain com.exemples.consumers.ConsumerAvroTopic"

.PHONY: json-producer
json-producer:
	sbt "runMain com.exemples.producers.ProducerJsonStringTopic"

.PHONY: json-consumer
json-consumer:
	sbt "runMain com.exemples.consumers.ConsumerJsonStringTopic"

.PHONY: string-producer
string-producer:
	sbt "runMain com.exemples.producers.ProducerStringTopic"

.PHONY: string-consumer
string-consumer:
	sbt "runMain com.exemples.consumers.ConsumerStringTopic"