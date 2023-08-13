package org.bitcoin.domain.type

enum class ExchangeType(
    val exchange: String
) {
    BITHUMB("bithumb"),
    UPBIT("upbit"),
    KOBIT("kobit"),
    NONE("none")
}