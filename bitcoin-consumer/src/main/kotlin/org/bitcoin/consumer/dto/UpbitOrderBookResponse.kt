package org.bitcoin.consumer.dto

data class UpbitOrderBookResponse (
    val type: String,
    val code: String,
    val timestamp: Long,
    val totalAskSize: Double,
    val totalBidSize: Double,
    val orderbook_units: List<OrderbookUnit>
) {
    data class OrderbookUnit (
        val askPrice: Double,
        val bidPrice: Double,
        val askSize: Double,
        val bidSize: Double
        ) {
    }
}