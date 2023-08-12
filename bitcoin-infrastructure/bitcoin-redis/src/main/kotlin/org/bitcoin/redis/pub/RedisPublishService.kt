package org.bitcoin.redis.pub

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RedisPublishService(
    private val redisTemplate: RedisTemplate<String, Any>
) {

    fun publish(channel: String, message: Any) {
        redisTemplate.convertAndSend(channel, message)
    }
}