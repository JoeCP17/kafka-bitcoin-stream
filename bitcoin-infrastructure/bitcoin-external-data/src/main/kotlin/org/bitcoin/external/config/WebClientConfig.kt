package org.bitcoin.external.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    /**
     * @description: bithumb 호가정보 조회
     */
    @Bean
    fun bitumbWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://api.bithumb.com/public/orderbook/")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
    }
}