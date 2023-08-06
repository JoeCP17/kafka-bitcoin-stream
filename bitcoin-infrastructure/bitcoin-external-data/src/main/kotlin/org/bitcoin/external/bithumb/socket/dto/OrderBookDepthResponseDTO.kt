package org.bitcoin.external.bithumb.socket.dto

// 변경호가에 대한 Response 응답
// 참고 :https://apidocs.bithumb.com/reference/%EB%B9%97%EC%8D%B8-%EA%B1%B0%EB%9E%98%EC%86%8C-%EC%A0%95%EB%B3%B4-%EC%88%98%EC%8B%A0
data class OrderBookDepthResponseDTO (
    val type: String,
    val content: ContentDTO
        ) {

    data class ContentDTO (
        val datetime: String,
        val list: MutableList<listItem>
    )

    data class listItem(
        val total: String,
        val orderType: String,
        val quantity: String,
        val price: String,
        val symbol: String
    )
}