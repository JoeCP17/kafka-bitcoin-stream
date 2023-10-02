package org.bitcoin.domain.bithumb.response

data class BithumbTickerResponse(
    val type: String,
    val content: TickerResponse
) {

    data class TickerResponse(
        val volumePower: String,
        val chgAmt: String,
        val chgRate: String,
        val prevClosePrice: String,
        val buyVolume: String,
        val sellVolume: String,
        val volume: String,
        val value: String,
        val highPrice: String,
        val lowPrice: String,
        val closePrice: String,
        val openPrice: String,
        val time: String,
        val date: String,
        val tickType: String,
        val symbol: String
    ) {

    }
}