package org.bitcoin.api.service

import com.kafka.scheduledata.controller.dto.AddCoinSymbolResponse
import com.kafka.scheduledata.controller.dto.AddCoinsymbolRequest
import org.bitcoin.infrastructure.jpa.CoinSymbol
import org.bitcoin.infrastructure.jpa.CoinSymbolRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CoinSymbolService(
    val coinSymbolRepository: CoinSymbolRepository
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