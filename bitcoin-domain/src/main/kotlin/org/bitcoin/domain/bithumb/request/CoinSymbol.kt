package org.bitcoin.domain.bithumb.request

import org.bitcoin.domain.type.ExchangeType
import java.time.LocalDateTime

data class CoinSymbol(
    var id: Long? = null,

    var symbol: String,

    var channel: String,

    var exchange: ExchangeType, // bithumb, upbit, kobit

    var createdDate: LocalDateTime,

    var updatedDate: LocalDateTime,
)