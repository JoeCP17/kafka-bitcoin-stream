package org.bitcoin.infrastructure.jpa.bithumb.entity

import org.bitcoin.domain.bithumb.request.CoinSymbol
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "coin_symbol")
class JpaCoinSymbol(
    symbol: String? = "",
    createdDate: LocalDateTime = LocalDateTime.now(),
    updatedDate: LocalDateTime = LocalDateTime.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coin_symbol_id", nullable = false)
    var id: Long = 0
        protected set

    @Column(name = "coin_symbol", length = 10, nullable = false)
    var symbol: String = symbol!!
        protected set

    @Column(name = "created_date", nullable = false)
    var createdDate: LocalDateTime = createdDate
        protected set

    @Column(name = "updated_date", nullable = false)
    var updatedDate: LocalDateTime = updatedDate
        protected set


    fun mapToCoinSymbolDTO(): CoinSymbol {
        return CoinSymbol(
            id = this.id,
            symbol = this.symbol,
            createdDate = this.createdDate,
            updatedDate = this.updatedDate
        )
    }

    companion object {
        fun toEntity(coinSymbol: CoinSymbol): JpaCoinSymbol {
            return JpaCoinSymbol(
                symbol = coinSymbol.symbol,
                createdDate = coinSymbol.createdDate,
                updatedDate = coinSymbol.updatedDate
            )
        }
    }
}