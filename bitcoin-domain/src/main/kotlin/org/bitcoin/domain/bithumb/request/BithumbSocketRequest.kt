package org.bitcoin.domain.bithumb.request

// 변경호가에 대한 Request 요청
data class BithumbSocketRequest(
    val type: String,
    val symbols: List<String>, // ex) ["BTC_KRW", "ETH_KRW"]
    val tickTypes: List<String> // ex) ["1H", "24H"]
) {

    companion object {
        fun createOrderBookDepthRequest(symbols: List<CoinSymbol>): BithumbSocketRequest {
            return BithumbSocketRequest(
                type = "orderbookdepth",
                symbols = addKRW(symbols),
                tickTypes = listOf("1H", "24H")
            )
        }

        fun createTickerRequest(symbols: List<CoinSymbol>): BithumbSocketRequest {
            return BithumbSocketRequest(
                type = "ticker",
                symbols = addKRW(symbols),
                tickTypes = listOf("1H", "24H")
            )
        }

        private fun addKRW(symbols: List<CoinSymbol>): List<String> {
            return symbols.map { "${it.symbol}_KRW" }
        }
    }
}