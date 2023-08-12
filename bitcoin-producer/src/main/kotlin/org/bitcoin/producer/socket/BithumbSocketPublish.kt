package org.bitcoin.producer.socket

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.domain.bithumb.response.OrderBookDepthResponse
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class BithumbSocketPublish(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    @EventListener(OrderBookDepthResponse::class)
    fun listenBithumbSocketListener(overBookDepthResponse: OrderBookDepthResponse) {
        println("Receive OrderBookDepthResponse: $overBookDepthResponse")
        kafkaTemplate.send("bithumb-stream", objectMapper.serialize(overBookDepthResponse))
    }

    fun <T> ObjectMapper.serialize(data: T): String = writeValueAsString(data)
}