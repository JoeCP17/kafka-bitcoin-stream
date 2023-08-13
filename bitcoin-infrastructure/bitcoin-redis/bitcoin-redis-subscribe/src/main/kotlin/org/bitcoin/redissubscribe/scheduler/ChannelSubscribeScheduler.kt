package org.bitcoin.redissubscribe.scheduler

import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.domain.type.ExchangeType
import org.bitcoin.infrastructure.jpa.bithumb.service.CoinSymbolRepository
import org.bitcoin.redissubscribe.listener.RedisSubscribeListener
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ChannelSubscribeScheduler(
    private val coinSymbolRepository: CoinSymbolRepository,
    private val redisSubscribeListener: RedisSubscribeListener
) {

    /**
     * @description : symbol를 읽어온 후, 10초마다 해당 symbolTopic을 구독한다. (bithumb, upbit, kobit)
     */

    @Transactional
    @Scheduled(cron = "10 * * * * *")
    fun subscribeRedisTopic() {
        val symbols = coinSymbolRepository.findAll()

        symbols.forEach { coinSymbol ->
            processingSubscribeSymbol(coinSymbol)
        }
    }


    private fun processingSubscribeSymbol(coinSymbol: CoinSymbol) {
        when (coinSymbol.exchange) {
            ExchangeType.BITHUMB -> {
                redisSubscribeListener.subscribeBithumb(ChannelTopic(coinSymbol.channel))
            }
            ExchangeType.UPBIT -> {
                redisSubscribeListener.subscribeUpbit(ChannelTopic(coinSymbol.channel))
            }

            ExchangeType.KOBIT -> {
                redisSubscribeListener.subscribeKobit(ChannelTopic(coinSymbol.channel))
            }

            else -> {
                throw InternalError("Not support exchange type")
            }
        }
    }
}