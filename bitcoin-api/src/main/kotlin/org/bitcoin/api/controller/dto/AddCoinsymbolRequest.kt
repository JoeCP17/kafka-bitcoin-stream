package org.bitcoin.api.controller.dto

import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbol
import java.time.LocalDateTime

data class AddCoinsymbolRequest(
    val symbol: String
) {
    fun convertToCoinSymbol(): JpaCoinSymbol {
        return JpaCoinSymbol(symbol = this.symbol, createdDate = LocalDateTime.now(), updatedDate = LocalDateTime.now())
    }
}

