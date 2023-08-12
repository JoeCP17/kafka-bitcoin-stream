package com.kafka.scheduledata.fetcher

import org.assertj.core.api.Assertions
import org.bitcoin.external.ExternalApplication
import org.bitcoin.external.bithumb.webflux.fetcher.BithumbFetcher
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

/**
 * 참조한 링크 : https://minkukjo.github.io/framework/2020/06/28/JUnit-23/
 * 생성자 주입 시, 클래스에 @Autowired 를 붙여주게되면 선언되어있는 constructor가 자동으로 주입되게 된다.
 */
@SpringBootTest(classes = [ExternalApplication::class, BithumbFetcher::class])
@DisplayName("WebClientFetcher 테스트")
class WebClientFetcherTest @Autowired constructor(
    var webClient: WebClient,
    var bithumbFetcher: BithumbFetcher
) {

    lateinit var code: String

    @BeforeEach
    fun setUp() {
        code = "BTC"

        webClient = WebClient.builder()
            .baseUrl("https://api.bithumb.com/public/orderbook/")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        bithumbFetcher = BithumbFetcher(webClient)
    }

    @Test
    @DisplayName("빗썸 API 호출 테스트")
    fun bithumbFetcherTest() {
        // when & then
        val bitumbOrderbook = bithumbFetcher.getBitumbOrderbook(code)
        Assertions.assertThat(bitumbOrderbook.status).isEqualTo("0000")
    }
}