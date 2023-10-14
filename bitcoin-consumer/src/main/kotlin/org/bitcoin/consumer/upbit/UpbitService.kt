package org.bitcoin.consumer.upbit

import org.bitcoin.domain.upbit.TickerResponse
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaOrderBookAsksRepository
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaOrderBookBidsRepository
import org.bitcoin.infrastructure.jpa.bithumb.entity.JpaOverBookRepository
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpbitService(
    val orderBookAsksRepository: JpaOrderBookAsksRepository,
    val orderBookBidsRepository: JpaOrderBookBidsRepository,
    val overBookRepository: JpaOverBookRepository,
    val simpMessageSendingOperations: SimpMessageSendingOperations
) {
    fun sendMessageToUser(response: TickerResponse) {
        simpMessageSendingOperations.convertAndSend("/topic/upbit", response)
    }
}