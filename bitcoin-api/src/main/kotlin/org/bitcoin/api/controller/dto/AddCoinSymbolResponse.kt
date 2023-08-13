package org.bitcoin.api.controller.dto

import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.domain.type.ExchangeType

data class AddCoinSymbolResponse(
    val id: Long,
    val symbol: String,
    var channel: String = "",
    val exchange: ExchangeType, // bithumb, upbit, kobit
    val createdDate: String,
    val updatedDate: String
) {
    companion object {
        fun of(coinSymbol: CoinSymbol): AddCoinSymbolResponse {
            return AddCoinSymbolResponse(
                id = coinSymbol.id!!,
                symbol = coinSymbol.symbol,
                channel = coinSymbol.channel,
                exchange = coinSymbol.exchange,
                createdDate = coinSymbol.createdDate.toString(),
                updatedDate = coinSymbol.updatedDate.toString()
            )
        }
    }
}