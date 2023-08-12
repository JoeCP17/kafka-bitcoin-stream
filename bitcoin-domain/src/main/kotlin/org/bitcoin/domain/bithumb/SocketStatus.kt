package org.bitcoin.domain.bithumb

data class SocketStatus(
    val status: String,
    val resmg: String
) {
    fun isConnect(): Boolean {
        return status == "0000"
    }
}