package org.bitcoin.infrastructure.jpa.bithumb.service

import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbolRepository
import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.domain.type.ExchangeType
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbol
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class CoinSymbolRepository(
    private val coinSymbolRepository: JpaCoinSymbolRepository
) {
    fun findBySymbolName(symbol: String): CoinSymbol {
        val symbolEntity = coinSymbolRepository.findBySymbol(symbol)

        return symbolEntity.mapToCoinSymbolDTO()
    }
    fun findByExchange(exchange: ExchangeType): List<CoinSymbol> {
        val symbolEntities = coinSymbolRepository.findAllByExchange(exchange)
        return symbolEntities.map { it.mapToCoinSymbolDTO() }
    }

    fun findAll(): List<CoinSymbol> {
        val symbolEntities = coinSymbolRepository.findAll()
        return symbolEntities.map { it.mapToCoinSymbolDTO() }
    }

    @Transactional
    fun save(coinSymbol: CoinSymbol): CoinSymbol {
        val jpaCoinSymbol = coinSymbolRepository.save(JpaCoinSymbol.toEntity(coinSymbol))

        return jpaCoinSymbol.mapToCoinSymbolDTO()
    }

}