package org.bitcoin.external.bithumb.socket.dto


// 변경호가에 대한 Request 요청
data class OrderBookDepthRequestDTO(
    val type: String,
    val symbols: List<String>
) {
}