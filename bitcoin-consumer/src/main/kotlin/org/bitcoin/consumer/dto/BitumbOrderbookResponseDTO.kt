package org.bitcoin.consumer.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.bitcoin.infrastructure.jpa.entity.bithumb.JpaOrderBookAsks
import org.bitcoin.infrastructure.jpa.entity.bithumb.JpaOverBook
import java.time.LocalDateTime

data class BitumbOrderbookResponseDTO(
    val status: String,
    val data: BithumbOrderbookDataDTO
) {
    data class BithumbOrderbookDataDTO(
        val timestamp: String,
        @JsonProperty("payment_currency")
        val paymentCurrency: String, // 주문통화 ( 코인 )
        @JsonProperty("order_currency")
        val orderCurrency: String, // 결제 통화 ( 마켓 )
        val bids: List<BithumbOrderbookBidsDTO>, // 매수 요청 내역
        val asks: List<BithumbOrderbookAsksDTO> // 매도 요청 내역
    ) {

        data class BithumbOrderbookBidsDTO(
            val quantity: String, // 수량
            val price: String // 가격
        )

        data class BithumbOrderbookAsksDTO(
            val quantity: String,
            val price: String
        ) {
            companion object {
                fun toEntity(asks: BithumbOrderbookAsksDTO, overBook: JpaOverBook): JpaOrderBookAsks {
                    return JpaOrderBookAsks(
                        quantity = asks.quantity,
                        price = asks.price,
                        overBookId = overBook,
                        createdDate = LocalDateTime.now(),
                        updatedDate = LocalDateTime.now()
                    )
                }
            }
        }
        fun toOverBookEntity(): JpaOverBook {
            return JpaOverBook(
                timestamp = this.timestamp,
                paymentCurrency = this.paymentCurrency,
                orderCurrency = this.orderCurrency,
                createdDate = LocalDateTime.now(),
                updatedDate = LocalDateTime.now()
            )
        }
    }
}