package com.kafka.scheduledata.fetcher.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BitumbOrderbookResponseDTO(
    val status: String,
    val data: bithumbOrderbookDataDTO
) {
    data class bithumbOrderbookDataDTO(
        val timestamp: String,
        @JsonProperty("payment_currency")
        val paymentCurrency: String, // 주문통화 ( 코인 )
        @JsonProperty("order_currency")
        val orderCurrency: String, // 결제 통화 ( 마켓 )
        val bids: List<bithumbOrderbookBidsDTO>, // 매수 요청 내역
        val asks: List<bithumbOrderbookAsksDTO> // 매도 요청 내역
    ) {

        data class bithumbOrderbookBidsDTO(
            val quantity: String, // 수량
            val price: String // 가격
        )

        data class bithumbOrderbookAsksDTO(
            val quantity: String,
            val price: String
        )
    }
}