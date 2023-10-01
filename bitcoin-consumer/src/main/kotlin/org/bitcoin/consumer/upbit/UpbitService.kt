package org.bitcoin.consumer.upbit

import org.bitcoin.domain.upbit.OrderBookResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpbitService {

    fun saveOrderBookData(deserializeData: OrderBookResponse) {
        TODO("Not yet implemented")
    }
}