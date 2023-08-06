package org.bitcoin.external.config


import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketHttpHeaders
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import java.net.URI
import javax.websocket.ContainerProvider

class ExternalsocketHandler(
    private var webSocketSession: WebSocketSession? = null,
    private val id: String,
    private val address: String,
    private val message: String
    ): WebSocketHandler {

    override fun afterConnectionEstablished(session: WebSocketSession) {
        webSocketSession!!.sendMessage(TextMessage(message))
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        println("hanleTransportError: " + exception.message + ", id:" + id )
    }

    override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus) {
        println("afterConnectionClosed: " + closeStatus.reason + ", id:" + id )
    }

    override fun supportsPartialMessages(): Boolean {
        return false
    }

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        val data = message.payload.toString()
        println("handleMessage: $data, id:$id")
    }

    fun connect() {
        val uri = URI(address)
        val headers = WebSocketHttpHeaders()
        val socketContainer = ContainerProvider.getWebSocketContainer()
        socketContainer.defaultMaxTextMessageBufferSize = 1024 * 1024

        val listenableFuture = StandardWebSocketClient(socketContainer).doHandshake(this, headers, uri)

        listenableFuture.addCallback(
            { result ->
                webSocketSession = result
            },
            { ex ->
                println("WebSocketClient connect failed" + "error:"+ ex.message + ", id:" + id)
            }
        )
    }

    fun disconnect() {
        webSocketSession!!.close()
    }
}