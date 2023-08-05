package com.kafka.scheduledata.controller.dto

import org.bitcoin.infrastructure.jpa.CoinSymbol
import java.time.LocalDateTime

data class AddCoinsymbolRequest(
    val symbol: String
) {
    fun convertToCoinSymbol(): CoinSymbol {
        return CoinSymbol(symbol = this.symbol, createdDate = LocalDateTime.now(), updatedDate = LocalDateTime.now())
    }
}

