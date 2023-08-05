
rootProject.name = "kafka-bitcoin-stream"
include("bitcoin-producer")
include("kafka-consumer")
include("bitcoin-infrastructure")
include("bitcoin-infrastructure:bitcoin-infrastructure-kafka")
findProject(":bitcoin-infrastructure:bitcoin-infrastructure-kafka")?.name = "bitcoin-infrastructure-kafka"
include("bitcoin-infrastructure:bitcoin-infrastructure-jpa")
findProject(":bitcoin-infrastructure:bitcoin-infrastructure-jpa")?.name = "bitcoin-infrastructure-jpa"
include("bitcoin-infrastructure:bitcoin-external-data")
findProject(":bitcoin-infrastructure:bitcoin-external-data")?.name = "bitcoin-external-data"
include("bitcoin-api")
