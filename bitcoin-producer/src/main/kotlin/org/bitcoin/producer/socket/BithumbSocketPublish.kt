package org.bitcoin.producer.socket

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.external.bithumb.socket.dto.OrderBookDepthResponseDTO
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BithumbSocketPublish(
    val kafkaTemplate: KafkaTemplate<String, String>,
    val objectMapper: ObjectMapper
) {

    @EventListener(classes = [OrderBookDepthResponseDTO::class])
    fun listenBithumbSocketListener(overBookDepthResponse: OrderBookDepthResponseDTO) {
        println("Receive OrderBookDepthResponse: $overBookDepthResponse")
        kafkaTemplate.send("bithumb-stream", objectMapper.serialize(overBookDepthResponse))
    }

    fun <T> ObjectMapper.serialize(data: T): String = writeValueAsString(data)
}