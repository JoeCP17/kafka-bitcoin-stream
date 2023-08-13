package org.bitcoin.infrastructure.jpa.bithumb.entity

import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.domain.type.ExchangeType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "coin_symbol")
class JpaCoinSymbol(
    symbol: String? = "",
    channelTopic: String? = "",
    exchange: ExchangeType = ExchangeType.NONE, // bithumb, upbit, kobit
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

    @Column(name = "channel_topic", length = 50, nullable = false)
    var channelTopic: String = channelTopic!!
        protected set

    @Column(name = "exchange", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    var exchange: ExchangeType = exchange
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
            channel = this.channelTopic,
            exchange = this.exchange,
            createdDate = this.createdDate,
            updatedDate = this.updatedDate
        )
    }

    companion object {
        fun toEntity(coinSymbol: CoinSymbol): JpaCoinSymbol {
            return JpaCoinSymbol(
                symbol = coinSymbol.symbol,
                channelTopic = coinSymbol.channel,
                exchange = coinSymbol.exchange,
                createdDate = coinSymbol.createdDate,
                updatedDate = coinSymbol.updatedDate
            )
        }
    }
}