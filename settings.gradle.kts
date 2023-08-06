
rootProject.name = "kafka-bitcoin-stream"
include("bitcoin-api")
include("bitcoin-producer")
include("bitcoin-consumer")
include("bitcoin-infrastructure")
include("bitcoin-infrastructure:bitcoin-infrastructure-kafka")
include("bitcoin-infrastructure:bitcoin-infrastructure-jpa")
include("bitcoin-infrastructure:bitcoin-external-data")
