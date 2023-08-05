package org.bitcoin.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface CoinSymbolRepository: JpaRepository<CoinSymbol, Long> {
}