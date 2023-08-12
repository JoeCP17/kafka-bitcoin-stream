package org.bitcoin.infrastructure.jpa.bithumb.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaCoinSymbolRepository: JpaRepository<JpaCoinSymbol, Long> {

    fun findBySymbol(symbol: String): JpaCoinSymbol

}