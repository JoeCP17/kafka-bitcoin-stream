package org.bitcoin.infrastructure.jpa.bithumb.service

import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbolRepository
import org.bitcoin.domain.bithumb.CoinSymbol
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

    fun findAll(): List<CoinSymbol> {
        val symbolEntities = coinSymbolRepository.findAll()
        return symbolEntities.map { it.mapToCoinSymbolDTO() }
    }

}