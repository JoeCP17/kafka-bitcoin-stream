package org.bitcoin.websocket.bithumb.handler

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.domain.bithumb.request.CoinSymbol
import org.bitcoin.domain.bithumb.request.BithumbSocket
import org.bitcoin.domain.bithumb.response.BithumbTicker
import org.bitcoin.domain.type.ExchangeType
import org.bitcoin.infrastructure.jpa.bithumb.service.CoinSymbolRepository
import org.bitcoin.redispublish.publish.RedisPublishService
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.CountDownLatch

@Component
class BithumbSocketHandler(
    private val objectMapper: ObjectMapper,
    private val bithumbRepository: CoinSymbolRepository,
    private val redisPublishService: RedisPublishService
): TextWebSocketHandler() {

    // 클라이언트가 접속을 종료할 때까지 대기하는 CountDownLatch
    private val closeLatch = CountDownLatch(1)

    // 클라이언트가 접속을 종료할 경우 발생하는 이벤트
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("[BITHUMB] Connection closed: ${status.code} - ${status.reason}")
        session.close()
        closeLatch.countDown()
    }

    // 클라이언트가 접속을 성공할 경우 발생하는 이벤트
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("[BITHUMB] Got Connect : ${session.id}")
        val depthRequest = BithumbSocket.createTickerRequest(findAllByExchange(ExchangeType.BITHUMB))
        session.sendMessage(TextMessage(objectMapper.writeValueAsString(depthRequest)))
    }

    // 클라이언트가 메시지를 보낼 경우 발생하는 이벤트
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("[BITHUMB] Got Message : ${message.payload}")

        try {
            val readTree = objectMapper.readTree(message.payload)

            if (!readTree.has("status")) {
                val response =
                    objectMapper.readValue(message.payload, BithumbTicker::class.java)

                redisPublishService.publish(
                    getChannelInSymbols(findAllByExchange(ExchangeType.BITHUMB)),
                    response
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun findAllByExchange(exchage: ExchangeType): List<CoinSymbol> {
        return bithumbRepository.findByExchange(exchage)
    }

    private fun getChannelInSymbols(symbols: List<CoinSymbol>): String {
        return symbols[0].channel
    }
}