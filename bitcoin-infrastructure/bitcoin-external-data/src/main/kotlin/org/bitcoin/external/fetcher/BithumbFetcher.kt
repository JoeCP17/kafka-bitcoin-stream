package org.bitcoin.external.fetcher

import org.bitcoin.external.fetcher.dto.BitumbOrderbookResponseDTO
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class BithumbFetcher(

    @Qualifier("bitumbWebClient")
    val bitumbWebClient: WebClient,
) {

    fun getBitumbOrderbook(code: String): BitumbOrderbookResponseDTO {
        return bitumbWebClient.get()
            .uri{builder -> builder.path("/$code").build()}
            .retrieve()
            .bodyToMono(BitumbOrderbookResponseDTO::class.java)
            .block()!!
    }
}