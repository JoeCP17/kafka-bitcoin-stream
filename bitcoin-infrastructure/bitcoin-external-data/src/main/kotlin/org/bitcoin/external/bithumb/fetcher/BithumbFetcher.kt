package org.bitcoin.external.bithumb.fetcher

import org.bitcoin.external.bithumb.fetcher.dto.BitumbOrderbookResponseDTO
import org.bitcoin.external.bithumb.socket.dto.OrderBookDepthRequestDTO
import org.bitcoin.external.bithumb.socket.dto.OrderBookDepthResponseDTO
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class BithumbFetcher(

    @Qualifier("bitumbWebClient")
    val bitumbWebClient: WebClient
) {

    fun getBitumbOrderbook(code: String): BitumbOrderbookResponseDTO {
        return bitumbWebClient.get()
            .uri{builder -> builder.path("/$code").build()}
            .retrieve()
            .bodyToMono(BitumbOrderbookResponseDTO::class.java)
            .block()!!
    }
}