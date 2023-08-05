
rootProject.name = "kafka-bitcoin-stream"
include("bitcoin-producer")
include("kafka-consumer")
include("bitcoin-infrastructure")
include("bitcoin-infrastructure:bitcoin-infrastructure-kafka")
findProject(":bitcoin-infrastructure:bitcoin-infrastructure-kafka")?.name = "bitcoin-infrastructure-kafka"
include("bitcoin-infrastructure:bitcoin-infrastructure-jpa")
findProject(":bitcoin-infrastructure:bitcoin-infrastructure-jpa")?.name = "bitcoin-infrastructure-jpa"
include("bitcoin-infrastructure:bitcoin-infrastructure-jpa")
findProject(":bitcoin-infrastructure:bitcoin-infrastructure-jpa")?.name = "bitcoin-infrastructure-jpa"
