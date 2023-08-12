package org.bitcoin.domain.bithumb.type


enum class TopicType(
    var topicName: String
) {
    BITHUMB("bithumb"),
    BITHUMB_STREAM("bithumb-stream"),
    COINONE("coinone"),
    UPBIT("upbit")
}