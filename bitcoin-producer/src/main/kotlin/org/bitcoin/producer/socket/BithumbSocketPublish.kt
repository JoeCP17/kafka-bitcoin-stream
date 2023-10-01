package org.bitcoin.producer.socket

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.domain.bithumb.response.OrderBookDepthResponse
import org.bitcoin.domain.bithumb.type.TopicType
import org.bitcoin.domain.upbit.OrderBookResponse
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class BithumbSocketPublish(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    @EventListener(OrderBookDepthResponse::class)
    fun listenBithumbSocketListener(overBookDepthResponse: OrderBookDepthResponse) {
        println("[BITHUMB] Receive OrderBookDepthResponse: $overBookDepthResponse")
        kafkaTemplate.send(TopicType.BITHUMB_STREAM.topicName, objectMapper.serialize(overBookDepthResponse))
    }

    @EventListener(OrderBookResponse::class)
    fun listenUpbitSocketListener(orderBookResponse: OrderBookResponse) {
        println("[UPBIT] Receive OrderBookResponse: $orderBookResponse")
        kafkaTemplate.send(TopicType.UPBIT.topicName, objectMapper.serialize(orderBookResponse))
    }

    fun <T> ObjectMapper.serialize(data: T): String = writeValueAsString(data)
}