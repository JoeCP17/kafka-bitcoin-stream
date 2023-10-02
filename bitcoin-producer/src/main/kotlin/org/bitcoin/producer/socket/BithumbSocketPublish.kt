package org.bitcoin.producer.socket

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.domain.bithumb.response.BithumbOrderBookDepthResponse
import org.bitcoin.domain.bithumb.response.BithumbTickerResponse
import org.bitcoin.domain.bithumb.type.TopicType
import org.bitcoin.domain.upbit.OrderBookResponse
import org.bitcoin.domain.upbit.TickerResponse
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class BithumbSocketPublish(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    @EventListener(BithumbTickerResponse::class)
    fun listenBithumbStreamSocketListener(tickerResponse: BithumbTickerResponse) {
        println("[BITHUMB] Receive OrderBookDepthResponse: $tickerResponse")
        kafkaTemplate.send(TopicType.BITHUMB_STREAM.topicName, objectMapper.serialize(tickerResponse))
    }

    @EventListener(TickerResponse::class)
    fun listenUpbitStreamSocketListener(tickerResponse: TickerResponse) {
        println("[UPBIT] Receive OrderBookResponse: $tickerResponse")
        kafkaTemplate.send(TopicType.UPBIT_STREAM.topicName, objectMapper.serialize(tickerResponse))
    }

    fun <T> ObjectMapper.serialize(data: T): String = writeValueAsString(data)
}