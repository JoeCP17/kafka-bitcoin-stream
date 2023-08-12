package org.bitcoin.domain.bithumb.request

import java.time.LocalDateTime

data class CoinSymbol(
    var id: Long? = null,

    var symbol: String,

    var createdDate: LocalDateTime,

    var updatedDate: LocalDateTime,
)