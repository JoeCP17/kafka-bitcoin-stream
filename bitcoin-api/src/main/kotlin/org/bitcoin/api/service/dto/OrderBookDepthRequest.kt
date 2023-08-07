package org.bitcoin.api.service.dto

import org.bitcoin.external.bithumb.socket.dto.OrderBookDepthRequestDTO

data class OrderBookDepthRequest(
    val type: String = "orderbookdepth",
    val symbols: List<String>
) {

    fun convertToExternalRequest(): OrderBookDepthRequestDTO {
        return OrderBookDepthRequestDTO(
            type = this.type,
            symbols = this.symbols
        )
    }

}
