package org.bitcoin.redissubscribe.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.listener.RedisMessageListenerContainer

@Configuration
class RedisSubscribeConfig(
    @Value("\${spring.redis.host}")
    val host: String,

    @Value("\${spring.redis.port}")
    val port: Int,
) {

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun redisMessageListenerContainer(
    ): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(redisConnectionFactory())
        return container
    }
}