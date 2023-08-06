package org.bitcoin.api.service

import org.bitcoin.api.controller.dto.AddCoinSymbolResponse
import org.bitcoin.api.controller.dto.AddCoinsymbolRequest
import org.bitcoin.infrastructure.jpa.entity.bithumb.JpaCoinSymbol
import org.bitcoin.infrastructure.jpa.entity.bithumb.CoinSymbolRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CoinSymbolService(
    private val coinSymbolRepository: CoinSymbolRepository
) {

    @Transactional
    fun addCoinSymbol(symbol: AddCoinsymbolRequest): AddCoinSymbolResponse {
       saveBitcoinSymbol(symbol.convertToCoinSymbol()).let { coinSymbol ->
           return AddCoinSymbolResponse.of(coinSymbol)
       }
    }

    private fun saveBitcoinSymbol(bitcoinSymbolJpa: JpaCoinSymbol): JpaCoinSymbol {
        return coinSymbolRepository.save(bitcoinSymbolJpa)
    }
}