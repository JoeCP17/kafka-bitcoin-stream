package org.bitcoin.producer.polling.scheduler

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.domain.bithumb.type.TopicType
import org.bitcoin.producer.polling.reader.BithumbReader
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class BithumbScheduler(
    val bithumbReader: BithumbReader,
    val kafkaTemplate: KafkaTemplate<String, String>,
    val objectMapper: ObjectMapper
) {

        @Transactional
        @Scheduled(cron = "3 * * * * *")
        fun getBitumbOrderbookData() {
            val bitcoinSymbolDataBySavedSymbolList =
                bithumbReader.getBitcoinSymbolDataBySavedSymbolList()

            bitcoinSymbolDataBySavedSymbolList.forEach {response ->
                kafkaTemplate.send(TopicType.BITHUMB.topicName, objectMapper.serialize(response))
            }
        }

        fun <T> ObjectMapper.serialize(data: T): String = writeValueAsString(data)
}