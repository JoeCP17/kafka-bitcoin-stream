package org.bitcoin.infrastructure.jpa

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "coin_symbol")
data class CoinSymbol(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val symbol: String,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime
) {
}