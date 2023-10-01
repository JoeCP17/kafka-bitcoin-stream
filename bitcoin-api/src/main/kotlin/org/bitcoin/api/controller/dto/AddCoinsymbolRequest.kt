package org.bitcoin.api.controller.dto

import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.domain.type.ExchangeType
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbol
import java.time.LocalDateTime

data class AddCoinsymbolRequest(
    val symbol: String,
    val exchange: String // bithumb, upbit, kobit
) {
    private val topicPath = "/symbol/"

    fun convertToCoinSymbol(): CoinSymbol {
        return CoinSymbol(
            symbol = this.symbol,
            channel = topicPath.plus(this.exchange).plus("/").plus(this.symbol),
            exchange = ExchangeType.valueOf(this.exchange),
            createdDate = LocalDateTime.now(),
            updatedDate = LocalDateTime.now()
        )
    }

}

