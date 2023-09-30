package org.bitcoin.domain.upbit

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderBookResponse (
    val type: String,
    val code: String,
    val timestamp: Long,
    @JsonProperty(namespace = "total_ask_size")
    val totalAskSize: Double,
    @JsonProperty(namespace = "total_bid_size")
    val totalBidSize: Double,
    val orderbook_units: List<OrderbookUnit>
        ) {
    data class OrderbookUnit (
        @JsonProperty(namespace = "ask_price")
        val askPrice: Double,
        @JsonProperty(namespace = "bid_price")
        val bidPrice: Double,
        @JsonProperty(namespace = "ask_size")
        val askSize: Double,
        @JsonProperty(namespace = "bid_size")
        val bidSize: Double
            ) {
    }
}