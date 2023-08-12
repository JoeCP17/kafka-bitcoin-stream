package org.bitcoin.api.controller.dto

import org.bitcoin.domain.bithumb.request.CoinSymbol
import java.time.LocalDateTime

data class AddCoinSymbolResponse(
    val id: Long,
    val symbol: String,
    var channel: String = "",
    val createdDate: String,
    val updatedDate: String
) {
    companion object {
        private const val CHANNEL_PATH = "/symbol/"

        fun of(coinSymbol: CoinSymbol): AddCoinSymbolResponse {
            return AddCoinSymbolResponse(
                id = coinSymbol.id!!,
                symbol = coinSymbol.symbol,
                channel =  CHANNEL_PATH.plus(coinSymbol.symbol),
                createdDate = coinSymbol.createdDate.toString(),
                updatedDate = coinSymbol.updatedDate.toString()
            )
        }
    }
}