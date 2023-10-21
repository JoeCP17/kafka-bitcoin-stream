package org.bitcoin.external.bithumb.webflux.fetcher

import org.bitcoin.domain.bithumb.response.BitumbOrderbook
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class BithumbFetcher(

    @Qualifier("bitumbWebClient")
    val bitumbWebClient: WebClient
) {

    fun getBitumbOrderbook(code: String): BitumbOrderbook {
        return bitumbWebClient.get()
            .uri{builder -> builder.path("/$code").build()}
            .retrieve()
            .bodyToMono(BitumbOrderbook::class.java)
            .block()!!
    }
}