package org.bitcoin.api.service.dto

data class OrderBookDepthRequest(
    val type: String = "orderbookdepth",
    val symbols: List<String>
) {
}
