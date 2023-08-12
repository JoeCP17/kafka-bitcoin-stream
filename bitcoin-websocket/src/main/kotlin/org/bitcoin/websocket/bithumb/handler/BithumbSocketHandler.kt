package org.bitcoin.websocket.bithumb.handler

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.domain.bithumb.request.OrderBookDepthRequest
import org.bitcoin.domain.bithumb.response.OrderBookDepthResponse
import org.bitcoin.infrastructure.jpa.bithumb.service.CoinSymbolRepository
import org.springframework.context.ApplicationEventPublisher
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
    val applicationEventPublisher: ApplicationEventPublisher
): TextWebSocketHandler() {

    // 클라이언트가 접속을 종료할 때까지 대기하는 CountDownLatch
    private val closeLatch = CountDownLatch(1)

    // 클라이언트가 접속을 종료할 경우 발생하는 이벤트
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("Connection closed: ${status.code} - ${status.reason}")
        session.close()
        closeLatch.countDown()
    }

    // 클라이언트가 접속을 성공할 경우 발생하는 이벤트
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("Got Connect : ${session.id}")
        val depthRequest = OrderBookDepthRequest.createRequest(bithumbRepository.findAll())
        session.sendMessage(TextMessage(objectMapper.writeValueAsString(depthRequest)))
    }

    // 클라이언트가 메시지를 보낼 경우 발생하는 이벤트
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("Got Message : ${message.payload}")

        try {
            val readTree = objectMapper.readTree(message.payload)

            if (!readTree.has("status")) {
                val response =
                    objectMapper.readValue(message.payload, OrderBookDepthResponse::class.java)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}