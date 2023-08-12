package org.bitcoin.producer.polling.reader

import org.bitcoin.domain.bithumb.response.BitumbOrderbookResponse
import org.bitcoin.external.bithumb.webflux.fetcher.BithumbFetcher
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbolRepository
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaCoinSymbol
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BithumbReader(
    val bithumbFetcher: BithumbFetcher,
    val jpaCoinSymbolRepository: JpaCoinSymbolRepository
) {
    fun getBitcoinSymbolDataBySavedSymbolList(): List<BitumbOrderbookResponse> =
        getAllBitcoinSymbol().stream()
            .map { bitcoinSymbol -> getBitumbOrderbookData(bitcoinSymbol.symbol) }
            .toList()

    private fun getAllBitcoinSymbol(): List<JpaCoinSymbol> {
        return jpaCoinSymbolRepository.findAll()
    }

    private fun getBitumbOrderbookData(code: String): BitumbOrderbookResponse {
        return bithumbFetcher.getBitumbOrderbook(code)
    }
}