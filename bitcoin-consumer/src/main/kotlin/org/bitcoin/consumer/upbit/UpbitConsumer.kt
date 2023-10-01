package org.bitcoin.consumer.upbit

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.domain.upbit.OrderBookResponse
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class UpbitConsumer(
    val objectMapper: ObjectMapper,
    val upbitService: UpbitService
) {

    @KafkaListener(topics = ["upbit"], groupId = "bitcoin")
    fun getUpbitOrderBookData(message: String) {
        val deserializeData =
            objectMapper.deserialize(message, OrderBookResponse::class.java)

        upbitService.saveOrderBookData(deserializeData)
    }


    fun <T> ObjectMapper.deserialize(data: String, clazz: Class<T>): T = readValue(data, clazz)
}