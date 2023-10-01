package org.bitcoin.websocket.upbit.handler

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.domain.type.ExchangeType
import org.bitcoin.domain.upbit.OrderBookResponse
import org.bitcoin.domain.upbit.SocketRequest
import org.bitcoin.infrastructure.jpa.bithumb.service.CoinSymbolRepository
import org.bitcoin.redispublish.publish.RedisPublishService
import org.springframework.stereotype.Component
import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.BinaryWebSocketHandler
import java.nio.ByteBuffer
import java.util.concurrent.CountDownLatch

@Component
class UpbitSocketHandler(
    private val objectMapper: ObjectMapper,
    private val symbolRepository: CoinSymbolRepository,
    private val redisPublishService: RedisPublishService
): BinaryWebSocketHandler() {

    private val closeLatch = CountDownLatch(1)

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("[UPBIT] Connection closed: ${status.code} - ${status.reason}")
        session.close()
        closeLatch.countDown()
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("[UPBIT] Got Connect : ${session.id}")

        val coinList =
            findAllByExchange(ExchangeType.UPBIT).stream().map { coin -> coin.symbol }.toList()

        val tickerRequest = SocketRequest.createBufferRequest(coinList)

        session.sendMessage(BinaryMessage(tickerRequest))
    }

    override fun handleBinaryMessage(session: WebSocketSession, message: BinaryMessage) {
        println("[UPBIT] Got Message : ${message.payload}")
        try {
            val decodeBinaryData = decodeBinaryData(message.payload)

            if (decodeBinaryData != null) {
                redisPublishService.publish(
                    getChannelInSymbols(findAllByExchange(ExchangeType.UPBIT)),
                    decodeBinaryData
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun findAllByExchange(exchange: ExchangeType): List<CoinSymbol> {
        return symbolRepository.findByExchange(exchange)
    }

    private fun getChannelInSymbols(symbols: List<CoinSymbol>): String {
        return symbols[0].channel
    }

    private fun decodeBinaryData(hexData: ByteBuffer): OrderBookResponse? {
        val data = hexData.array()
        val decodeData = String(data, Charsets.UTF_8)
        return objectMapper.readValue(decodeData, OrderBookResponse::class.java)
    }
}