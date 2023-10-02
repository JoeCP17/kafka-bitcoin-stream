package org.bitcoin.redissubscribe.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.domain.upbit.OrderBookResponse
import org.bitcoin.domain.upbit.TickerResponse
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Service

@Service
class UpbitChannelSubscribeService(
    private val objectMapper: ObjectMapper,
    private val applicationEventPublisher: ApplicationEventPublisher
): MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        println("[UPBIT] message : ${String(message.body)}")
        val response = objectMapper.readValue(message.body, TickerResponse::class.java)

        applicationEventPublisher.publishEvent(response)
    }
}