package org.bitcoin.api.service

import org.bitcoin.api.controller.dto.AddCoinSymbolResponse
import org.bitcoin.api.controller.dto.AddCoinsymbolRequest
import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.infrastructure.jpa.bithumb.service.CoinSymbolRepository
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

    private fun saveBitcoinSymbol(bitcoinSymbol: CoinSymbol): CoinSymbol {
        return coinSymbolRepository.save(bitcoinSymbol)
    }
}