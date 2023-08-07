package org.bitcoin.producer.polling.reader

import org.bitcoin.external.bithumb.webflux.dto.BitumbOrderbookResponseDTO
import org.bitcoin.external.bithumb.webflux.BithumbFetcher
import org.bitcoin.infrastructure.jpa.entity.bithumb.CoinSymbolRepository
import org.bitcoin.infrastructure.jpa.entity.bithumb.JpaCoinSymbol
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BithumbReader(
    val bithumbFetcher: BithumbFetcher,
    val coinSymbolRepository: CoinSymbolRepository
) {
    fun getBitcoinSymbolDataBySavedSymbolList(): List<BitumbOrderbookResponseDTO> =
        getAllBitcoinSymbol().stream()
            .map { bitcoinSymbol -> getBitumbOrderbookData(bitcoinSymbol.symbol) }
            .toList()

    private fun getBitcoinSymbolBySymbolName(symbol: String): JpaCoinSymbol {
        return coinSymbolRepository.findBySymbol(symbol)
    }

    private fun getAllBitcoinSymbol(): List<JpaCoinSymbol> {
        return coinSymbolRepository.findAll()
    }

    private fun getBitumbOrderbookData(code: String): BitumbOrderbookResponseDTO {
        return bithumbFetcher.getBitumbOrderbook(code)
    }
}