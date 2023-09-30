package org.bitcoin.websocket.upbit.service

import org.bitcoin.external.bithumb.path.BitcoinWebsocketPath
import org.bitcoin.websocket.bithumb.handler.BithumbSocketHandler
import org.bitcoin.websocket.upbit.handler.UpbitSocketHandler
import org.springframework.stereotype.Service
import org.springframework.web.socket.client.WebSocketConnectionManager
import org.springframework.web.socket.client.standard.StandardWebSocketClient

@Service
class UpbitService(
    private val upbitSocketHandler: UpbitSocketHandler
) {
    /**
     * @description : 비트코인 웹소켓을 통해 실시간 주문 체결 내역을 가져온다.
     * @param request : 요청 객체
     */
    fun getTickerDepth() {
        val client = StandardWebSocketClient()
        try {
           client.taskExecutor!!.execute {
               val manager = WebSocketConnectionManager(
                   client,
                   upbitSocketHandler,
                   BitcoinWebsocketPath.upbit
               )
               manager.start()
               println("[UPBIT] connection to : ${BitcoinWebsocketPath.upbit}")
           }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}