package org.bitcoin.api.controller.dto

import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbol
import java.time.LocalDateTime

data class AddCoinSymbolResponse(
    val id: Long,
    val symbol: String,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime
) {
    companion object {
        fun of(coinSymbol: CoinSymbol): AddCoinSymbolResponse {
            return AddCoinSymbolResponse(
                id = coinSymbol.id!!,
                symbol = coinSymbol.symbol,
                createdDate = coinSymbol.createdDate,
                updatedDate = coinSymbol.updatedDate
            )
        }
    }
}