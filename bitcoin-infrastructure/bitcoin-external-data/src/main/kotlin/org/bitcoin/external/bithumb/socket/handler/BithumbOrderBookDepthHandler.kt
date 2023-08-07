package org.bitcoin.external.bithumb.socket.handler

import org.bitcoin.external.bithumb.socket.dto.OrderBookDepthResponseDTO
import org.springframework.context.ApplicationEventPublisher
import org.springframework.messaging.simp.stomp.StompFrameHandler
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.stereotype.Service
import java.lang.reflect.Type

@Service
class BithumbOrderBookDepthHandler(
    val applicationEventPublisher: ApplicationEventPublisher
): StompFrameHandler {

    override fun getPayloadType(headers: StompHeaders): Type {
        println("PayLoad Type: ${headers.destination}")
        return headers.destination?.javaClass!!
    }

    override fun handleFrame(headers: StompHeaders, payload: Any?) {
        println("Response: $payload")
        val response = payload as OrderBookDepthResponseDTO
        applicationEventPublisher.publishEvent(response)
    }
}