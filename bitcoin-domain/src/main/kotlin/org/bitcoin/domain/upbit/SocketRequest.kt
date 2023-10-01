package org.bitcoin.domain.upbit

data class SocketRequest(
    val ticket: Ticket,
    val type: Type,
    val format: Format
) {
    data class Ticket (
        val ticket: String = "test example"
            ) {
    }

    data class Type (
        val type: String,
        val codes: List<String>
            ) {
    }

    data class Format (
        val format: String = "DEFAULT"
            ) {
    }

    companion object {
        fun createRequest(codes: List<String>): SocketRequest {
            return SocketRequest(
                ticket = Ticket(),
                type = Type("orderbook" ,addKRW(codes)),
                format = Format("DEFAULT")
            )
        }

        private fun addKRW(codes: List<String>): List<String> {
            return codes.map { code -> "KRW-$code" }
        }

        fun createBufferRequest(codes: List<String>): ByteArray {
            val requestValue = StringBuilder().apply {
                append("[{\"ticket\":\"test example\"},{\"type\":\"orderbook\",\"codes\":[")
                codes.forEachIndexed { index, code ->
                    append("\"KRW-$code\"")
                    if (index != codes.size - 1) {
                        append(",")
                    }
                }
                append("]},{\"format\":\"DEFAULT\"}]")
            }

            return requestValue.toString().toByteArray()
        }
    }
}
