package org.bitcoin.external.bithumb.socket.handler

import org.bitcoin.external.bithumb.socket.dto.SocketStatusDTO
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.messaging.simp.stomp.StompSessionHandler
import org.springframework.stereotype.Service
import java.lang.reflect.Type

@Service
class BithumbSocketConnectHandler(
): StompSessionHandler {
    override fun getPayloadType(headers: StompHeaders): Type {
        println("PayLoad Type: ${headers.destination}")
        return headers.destination?.javaClass!!
    }

    override fun handleFrame(headers: StompHeaders, payload: Any?) {
        val response = payload as SocketStatusDTO
        println("Response: $response")

        if (response.isConnect()) {
            println("Connected Bithumb Socket")
        } else {
            throw Exception("Bithumb Socket Connect Error")
        }
    }

    override fun afterConnected(session: StompSession, connectedHeaders: StompHeaders) {
        session.disconnect()
        println("Disconnected : ${connectedHeaders.destination} ${session.sessionId}")
    }

    override fun handleException(
        session: StompSession,
        command: StompCommand?,
        headers: StompHeaders,
        payload: ByteArray,
        exception: Throwable
    ) {
        println("Exception: ${exception.message}")
    }

    override fun handleTransportError(session: StompSession, exception: Throwable) {
        println("Transport Error: ${exception.message}")
    }
}