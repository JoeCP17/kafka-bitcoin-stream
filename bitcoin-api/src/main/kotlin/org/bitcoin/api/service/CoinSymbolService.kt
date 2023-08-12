package org.bitcoin.api.service

import org.bitcoin.api.controller.dto.AddCoinSymbolResponse
import org.bitcoin.api.controller.dto.AddCoinsymbolRequest
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbol
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbolRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CoinSymbolService(
    private val jpaCoinSymbolRepository: JpaCoinSymbolRepository
) {

    @Transactional
    fun addCoinSymbol(symbol: AddCoinsymbolRequest): AddCoinSymbolResponse {
       saveBitcoinSymbol(symbol.convertToCoinSymbol()).let { coinSymbol ->
           return AddCoinSymbolResponse.of(coinSymbol)
       }
    }

    private fun saveBitcoinSymbol(bitcoinSymbolJpa: JpaCoinSymbol): JpaCoinSymbol {
        return jpaCoinSymbolRepository.save(bitcoinSymbolJpa)
    }

    private fun getOverBookSymbolAndMapKRW(): List<String> {
        return jpaCoinSymbolRepository.findAll().map { coin -> coin.symbol + "_KRW" }
    }
}