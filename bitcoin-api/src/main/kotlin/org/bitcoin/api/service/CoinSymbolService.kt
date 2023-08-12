package org.bitcoin.api.service

import org.bitcoin.api.controller.dto.AddCoinSymbolResponse
import org.bitcoin.api.controller.dto.AddCoinsymbolRequest
import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.infrastructure.jpa.bithumb.service.CoinSymbolRepository
import org.bitcoin.redispublish.publish.RedisPublishService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CoinSymbolService(
    private val coinSymbolRepository: CoinSymbolRepository,
    private val redisPublishService: RedisPublishService
) {

    @Transactional
    fun addCoinSymbol(symbol: AddCoinsymbolRequest): AddCoinSymbolResponse {
       saveBitcoinSymbol(symbol.convertToCoinSymbol()).let { coinSymbol ->
           val response = AddCoinSymbolResponse.of(coinSymbol)
           sendRedisMessage(response)
           return response
       }
    }

    private fun saveBitcoinSymbol(bitcoinSymbolJpa: CoinSymbol): CoinSymbol {
        return coinSymbolRepository.save(bitcoinSymbolJpa)
    }

    private fun sendRedisMessage(response: AddCoinSymbolResponse) {
        redisPublishService.publish(response.channel, response)
    }
}