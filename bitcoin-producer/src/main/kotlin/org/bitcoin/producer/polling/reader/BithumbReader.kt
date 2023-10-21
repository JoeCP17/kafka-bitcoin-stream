package org.bitcoin.producer.polling.reader

import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.domain.bithumb.response.BitumbOrderbook
import org.bitcoin.external.bithumb.webflux.fetcher.BithumbFetcher
import org.bitcoin.infrastructure.jpa.bithumb.service.CoinSymbolRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BithumbReader(
    val bithumbFetcher: BithumbFetcher,
    val coinSymbolRepository: CoinSymbolRepository
) {
    fun getBitcoinSymbolDataBySavedSymbolList(): List<BitumbOrderbook> =
        getAllBitcoinSymbol().stream()
            .map { bitcoinSymbol -> getBitumbOrderbookData(bitcoinSymbol.symbol) }
            .toList()

    private fun getAllBitcoinSymbol(): List<CoinSymbol> {
        return coinSymbolRepository.findAll()
    }

    private fun getBitumbOrderbookData(code: String): BitumbOrderbook {
        return bithumbFetcher.getBitumbOrderbook(code)
    }
}