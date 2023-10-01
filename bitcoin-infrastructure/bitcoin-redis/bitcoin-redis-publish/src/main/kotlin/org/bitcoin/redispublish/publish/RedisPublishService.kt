package org.bitcoin.redispublish.publish

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisPublishService(
    private val redisTemplate: RedisTemplate<String, Any>
) {

    fun publish(channel: String, message: Any) {
        redisTemplate.convertAndSend(channel, message)
    }
}