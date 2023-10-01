package org.bitcoin.consumer.bithumb

import org.bitcoin.consumer.dto.BitumbOrderbookResponseDTO
import org.bitcoin.domain.bithumb.response.OrderBookDepthResponse
import org.bitcoin.infrastructure.jpa.bithumb.entity.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BithumbService(
    val orderBookAsksRepository: JpaOrderBookAsksRepository,
    val orderBookBidsRepository: JpaOrderBookBidsRepository,
    val overBookRepository: JpaOverBookRepository
) {

    @Transactional
    fun saveOrderBookData(response: BitumbOrderbookResponseDTO) {
        val findOrderBook = findOrderBookByOrderCurrency(response.data.orderCurrency)

        findOrderBook?.run {
            saveOrderBookAsks(response, findOrderBook)
            saveOrderBookBids(response, findOrderBook)

        } ?: run {
            val overBook = saveOverBook(response)
            saveOrderBookAsks(response, overBook)
            saveOrderBookBids(response, overBook)
        }
    }

    fun saveOrderBookStreamData(deserializeData: OrderBookDepthResponse) {
        TODO("Not yet implemented")
    }

    private fun saveOverBook(response: BitumbOrderbookResponseDTO): JpaOverBook {
        return toOverBookEntityBy(response).let {
            overBookRepository.save(it)
        }
    }

    private fun saveOrderBookAsks(response: BitumbOrderbookResponseDTO, overBook: JpaOverBook) {
        response.data.asks.forEach { it ->
            toAsksEntityBy(it.quantity, it.price, overBook).let {
                orderBookAsksRepository.save(it)
            }
        }
    }

    private fun saveOrderBookBids(response: BitumbOrderbookResponseDTO, overBook: JpaOverBook) {
        response.data.bids.forEach { it ->
            toBidsEntityBy(it.quantity, it.price, overBook).let {
                orderBookBidsRepository.save(it)
            }
        }
    }

    private fun toOverBookEntityBy(response: BitumbOrderbookResponseDTO): JpaOverBook =
        response.data.toOverBookEntity()


    private fun toAsksEntityBy(quantity: String, price: String, overBook: JpaOverBook): JpaOrderBookAsks =
        JpaOrderBookAsks(quantity = quantity,
                         price = price,
                         overBookId = overBook)


    private fun toBidsEntityBy(quantity: String, price: String, overBook: JpaOverBook): JpaOrderBookBids =
        JpaOrderBookBids(quantity = quantity,
            price = price,
            overBookId = overBook)



    // 값이 없을 경우 save 처리를 하기 위해 ?로 설정
    private fun findOrderBookByOrderCurrency(symbol: String): JpaOverBook? =
        overBookRepository.findByOrderCurrency(symbol)

}