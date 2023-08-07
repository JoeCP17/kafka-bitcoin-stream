package org.bitcoin.external.bithumb.socket.dto

data class SocketStatusDTO(
    val status: String,
    val resmg: String
) {
    fun isConnect(): Boolean {
        return status == "0000"
    }
}