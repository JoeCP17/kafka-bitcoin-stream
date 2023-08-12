package org.bitcoin.api.controller.dto

import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbol
import java.time.LocalDateTime

data class AddCoinSymbolResponse(
    val id: Long,
    val symbol: String,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime
) {
    companion object {
        fun of(jpaCoinSymbol: JpaCoinSymbol): AddCoinSymbolResponse {
            return AddCoinSymbolResponse(
                id = jpaCoinSymbol.id,
                symbol = jpaCoinSymbol.symbol,
                createdDate = jpaCoinSymbol.createdDate,
                updatedDate = jpaCoinSymbol.updatedDate
            )
        }
    }
}