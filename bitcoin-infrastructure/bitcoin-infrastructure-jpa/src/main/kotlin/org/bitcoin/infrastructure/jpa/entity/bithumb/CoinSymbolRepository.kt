package org.bitcoin.infrastructure.jpa.entity.bithumb

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CoinSymbolRepository: JpaRepository<JpaCoinSymbol, Long> {

    fun findBySymbol(symbol: String): JpaCoinSymbol

}