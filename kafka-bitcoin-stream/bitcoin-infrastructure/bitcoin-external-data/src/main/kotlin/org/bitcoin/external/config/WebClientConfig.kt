package org.bitcoin.external.config

import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Slf4j
@Configuration
class WebClientConfig {

    /**
     * @description: bithumb 호가정보 조회
     */
    @Bean(name = ["bitumbWebClient"])
    fun bitumbWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://api.bithumb.com/public/orderbook/")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
    }
}