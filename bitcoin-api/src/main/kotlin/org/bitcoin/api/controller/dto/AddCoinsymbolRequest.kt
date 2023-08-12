package org.bitcoin.api.controller.dto

import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbol
import java.time.LocalDateTime

data class AddCoinsymbolRequest(
    val symbol: String
) {
    fun convertToCoinSymbol(): CoinSymbol {
        return CoinSymbol(symbol = this.symbol, createdDate = LocalDateTime.now(), updatedDate = LocalDateTime.now())
    }
}

