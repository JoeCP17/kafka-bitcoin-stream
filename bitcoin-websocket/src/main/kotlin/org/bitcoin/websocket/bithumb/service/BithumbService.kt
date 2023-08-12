package org.bitcoin.websocket.bithumb.service

import org.bitcoin.external.bithumb.path.BitcoinWebsocketPath
import org.bitcoin.websocket.bithumb.handler.BithumbSocketHandler
import org.springframework.stereotype.Service
import org.springframework.web.socket.client.WebSocketConnectionManager
import org.springframework.web.socket.client.standard.StandardWebSocketClient

@Service
class BithumbService(
    private val bithumbSocketHandler: BithumbSocketHandler
) {
    /**
     * @description : 비트코인 웹소켓을 통해 실시간 주문 체결 내역을 가져온다.
     * @param request : 요청 객체
     */
    fun getOrderBootDepth() {
        val client = StandardWebSocketClient()
        try {
           client.taskExecutor!!.execute {
               val manager = WebSocketConnectionManager(
                   client,
                   bithumbSocketHandler,
                   BitcoinWebsocketPath.bithumb
               )
               manager.start()
               println("connection to : ${BitcoinWebsocketPath.bithumb}")
           }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}