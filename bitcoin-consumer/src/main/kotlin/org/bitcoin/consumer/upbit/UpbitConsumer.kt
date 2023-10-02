package org.bitcoin.consumer.upbit

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.domain.upbit.TickerResponse
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class UpbitConsumer(
    val objectMapper: ObjectMapper,
    val upbitService: UpbitService
) {

    @KafkaListener(topics = ["upbit-stream"], groupId = "bitcoin")
    fun getUpbitOrderBookData(message: String) {
        val deserializeData =
            objectMapper.deserialize(message, TickerResponse::class.java)

        println(deserializeData)
    }


    fun <T> ObjectMapper.deserialize(data: String, clazz: Class<T>): T = readValue(data, clazz)
}