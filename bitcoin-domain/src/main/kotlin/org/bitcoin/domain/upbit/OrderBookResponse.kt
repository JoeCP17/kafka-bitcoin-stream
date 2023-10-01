package org.bitcoin.domain.upbit

data class OrderBookResponse (
    val type: String,
    val code: String,
    val timestamp: Long,
    val total_ask_size: Double,
    val total_bid_size: Double,
    val orderbook_units: List<OrderbookUnit>
        ) {
    data class OrderbookUnit (
        val ask_price: Double,
        val bid_price: Double,
        val ask_size: Double,
        val bid_size: Double
            ) {
    }
}