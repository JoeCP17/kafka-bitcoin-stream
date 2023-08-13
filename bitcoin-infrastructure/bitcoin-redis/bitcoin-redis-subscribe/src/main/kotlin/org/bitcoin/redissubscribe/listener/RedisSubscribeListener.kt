package org.bitcoin.redissubscribe.listener

import org.bitcoin.redissubscribe.service.BithumbChannelSubscribeService
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class RedisSubscribeListener(
    private val bithumbChannelSubscribeService: BithumbChannelSubscribeService,
    private val upbitChannelSubscribeService: BithumbChannelSubscribeService,
    private val kobitChannelSubscribeService: BithumbChannelSubscribeService,
    private val redisMessageListenerContainer: RedisMessageListenerContainer
) {

    fun subscribeBithumb(topic: ChannelTopic) {
        redisMessageListenerContainer
            .addMessageListener(bithumbChannelSubscribeService, topic)
    }

    fun subscribeUpbit(channelTopic: ChannelTopic) {
        redisMessageListenerContainer
            .addMessageListener(upbitChannelSubscribeService, channelTopic)
    }

    fun subscribeKobit(channelTopic: ChannelTopic) {
        redisMessageListenerContainer
            .addMessageListener(kobitChannelSubscribeService, channelTopic)
    }
}