
rootProject.name = "kafka-bitcoin-stream"
include("bitcoin-api")
include("bitcoin-websocket")
include("bitcoin-producer")
include("bitcoin-consumer")
include("bitcoin-infrastructure")
include("bitcoin-infrastructure:bitcoin-infrastructure-kafka")
include("bitcoin-infrastructure:bitcoin-infrastructure-jpa")
include("bitcoin-infrastructure:bitcoin-external-data")
include("bitcoin-domain")
include("bitcoin-infrastructure:bitcoin-redis")
findProject(":bitcoin-infrastructure:bitcoin-redis")?.name = "bitcoin-redis"
