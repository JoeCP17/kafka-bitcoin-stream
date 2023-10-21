package org.bitcoin.producer.socket

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.domain.bithumb.response.BithumbTicker
import org.bitcoin.domain.type.ExchangeType
import org.bitcoin.domain.upbit.TickerResponse
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class BithumbSocketPublish(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    @EventListener(BithumbTicker::class)
    fun listenBithumbStreamSocketListener(tickerResponse: BithumbTicker) {
        println("[BITHUMB] Receive OrderBookDepthResponse: $tickerResponse")
        kafkaTemplate.send(ExchangeType.BITHUMB_STREAM.exchange, objectMapper.serialize(tickerResponse))
    }

    @EventListener(TickerResponse::class)
    fun listenUpbitStreamSocketListener(tickerResponse: TickerResponse) {
        println("[UPBIT] Receive OrderBookResponse: $tickerResponse")
        kafkaTemplate.send(ExchangeType.UPBIT_STREAM.exchange, objectMapper.serialize(tickerResponse))
    }

    fun <T> ObjectMapper.serialize(data: T): String = writeValueAsString(data)
}