package org.bitcoin.consumer.upbit

import org.bitcoin.domain.upbit.OrderBookResponse
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaOrderBookAsksRepository
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaOrderBookBidsRepository
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaOverBookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpbitService(
    val orderBookAsksRepository: JpaOrderBookAsksRepository,
    val orderBookBidsRepository: JpaOrderBookBidsRepository,
    val overBookRepository: JpaOverBookRepository
) {

}