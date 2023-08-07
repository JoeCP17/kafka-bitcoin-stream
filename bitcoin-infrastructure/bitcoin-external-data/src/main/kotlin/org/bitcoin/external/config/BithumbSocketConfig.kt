package org.bitcoin.external.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.bitcoin.external.bithumb.socket.dto.OrderBookDepthRequestDTO
import org.bitcoin.external.bithumb.socket.handler.BithumbOrderBookDepthHandler
import org.bitcoin.external.bithumb.socket.handler.BithumbSocketConnectHandler
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.WebSocketStompClient

@Component
class BithumbSocketConfig(
    private val bithumbSocketConnectHandler: BithumbSocketConnectHandler,
    private val bithumbOrderBookDepthHandler: BithumbOrderBookDepthHandler,
    val objectMapper: ObjectMapper
) {
    private val bithumbSocket = WebSocketStompClient()


    fun connectBithumbSocket(request: OrderBookDepthRequestDTO) {
        bithumbSocket.connect(getBithumbSocketPath(), bithumbSocketConnectHandler)
            .addCallback({stompSession ->
            println("Connected Bithumb Socket")
            stompSession!!.subscribe(settingToOrderBookDepth(request), bithumbOrderBookDepthHandler)
        }, {throwable ->
            println("Exception: ${throwable.message}")
        })
    }

    fun disconnectBithumbSocket() {
        bithumbSocket.stop()
    }

    private final fun getBithumbSocketPath(): String {
        return "wss://pubwss.bithumb.com/pub/ws"
    }

    private fun WebSocketStompClient(): WebSocketStompClient {
        return WebSocketStompClient()
    }

    private fun settingToOrderBookDepth(request: OrderBookDepthRequestDTO): String {
        return objectMapper.writeValueAsString(request)
    }
}