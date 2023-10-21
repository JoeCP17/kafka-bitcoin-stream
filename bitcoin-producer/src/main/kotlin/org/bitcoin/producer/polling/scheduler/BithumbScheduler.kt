package org.bitcoin.producer.polling.scheduler

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.producer.polling.reader.BithumbReader
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class BithumbScheduler(
    val bithumbReader: BithumbReader,
    val kafkaTemplate: KafkaTemplate<String, String>,
    val objectMapper: ObjectMapper
) {

    // TODO : 현재는 사용 안함 추후 서킷브레이커 적용 후, 살릴 예정

//        @Transactional
//        @Scheduled(cron = "3 * * * * *")
//        fun getBitumbOrderbookData() {
//            val bitcoinSymbolDataBySavedSymbolList =
//                bithumbReader.getBitcoinSymbolDataBySavedSymbolList()
//
//            bitcoinSymbolDataBySavedSymbolList.forEach {response ->
//                kafkaTemplate.send(TopicType.BITHUMB.topicName, objectMapper.serialize(response))
//            }
//        }

        fun <T> ObjectMapper.serialize(data: T): String = writeValueAsString(data)
}