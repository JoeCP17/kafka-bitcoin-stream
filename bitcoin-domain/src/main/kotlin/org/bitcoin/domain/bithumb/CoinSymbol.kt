package org.bitcoin.domain.bithumb

import java.time.LocalDateTime

data class CoinSymbol(
    var id: Long,

    var symbol: String,

    var createdDate: LocalDateTime,

    var updatedDate: LocalDateTime,
)