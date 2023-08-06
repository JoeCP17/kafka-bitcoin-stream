package org.bitcoin.producer.polling.service

import lombok.extern.slf4j.Slf4j
import org.bitcoin.infrastructure.jpa.entity.bithumb.CoinSymbolRepository
import org.bitcoin.infrastructure.jpa.entity.bithumb.JpaCoinSymbol
import org.bitcoin.producer.polling.dto.AddCoinSymbolResponse
import org.bitcoin.producer.polling.dto.CoinsymbolRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@Transactional(readOnly = true)
class BithumbService(
    val kafkaTemplate: KafkaTemplate<String, String>,
    val coinSymbolRepository: CoinSymbolRepository
) {

    fun sendMessageTest(message: String) {
        println("Sending message to kafka: $message")
        kafkaTemplate.send("test", message)
    }

    @Transactional
    fun addCoinSymbol(symbol: CoinsymbolRequest): AddCoinSymbolResponse {
         return symbol.convertToCoinSymbol().let { bitcoinSymbol ->
             AddCoinSymbolResponse.of( saveBitcoinSymbol(bitcoinSymbol))
        }
    }

    private fun saveBitcoinSymbol(coinSymbol: JpaCoinSymbol): JpaCoinSymbol {
        return coinSymbolRepository.save(coinSymbol)
    }
}