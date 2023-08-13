package org.bitcoin.redissubscribe.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Service

@Service
class BithumbChannelSubscribeService(
    private val objectMapper: ObjectMapper,
    private val applicationEventPublisher: ApplicationEventPublisher
): MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {

    }
}