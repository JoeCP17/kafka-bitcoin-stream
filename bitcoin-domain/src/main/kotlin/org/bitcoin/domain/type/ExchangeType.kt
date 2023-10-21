package org.bitcoin.domain.type

enum class ExchangeType(
    val exchange: String
) {
    BITHUMB("bithumb"),
    BITHUMB_STREAM("bithumb-stream"),
    UPBIT("upbit"),
    UPBIT_STREAM("upbit-stream"),
    KOBIT("kobit"),
    NONE("none")
}