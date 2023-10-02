package org.bitcoin.consumer.bithumb

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.consumer.dto.BitumbOrderbookResponseDTO
import org.bitcoin.domain.bithumb.response.BithumbOrderBookDepthResponse
import org.bitcoin.domain.bithumb.response.BithumbTickerResponse
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class BithumbConsumer(
    val objectMapper: ObjectMapper,
    val bithumbService: BithumbService
) {

    // TODO : 웹소켓을 연동하여 데이터 보내기

    @KafkaListener(topics = ["bithumb"], groupId = "bitcoin")
    fun getBitumbOrderBookData(message: String) {
        val deserializeData =
            objectMapper.deserialize(message, BithumbTickerResponse::class.java)

        println(deserializeData)
    }

    @KafkaListener(topics = ["bithumb-stream"], groupId = "bitcoin")
    fun getBitumbOrderBookStreamData(message: String) {
        val deserializeData =
            objectMapper.deserialize(message, BithumbTickerResponse::class.java)

        println(deserializeData)
    }

    fun <T> ObjectMapper.deserialize(data: String, clazz: Class<T>): T = readValue(data, clazz)

}