package org.bitcoin.websocket

import org.bitcoin.websocket.bithumb.service.BithumbService
import org.bitcoin.websocket.upbit.service.UpbitService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.bitcoin"])
class BitcoinWebsocketApplication

fun main(args: Array<String>) {
	val ctx = runApplication<BitcoinWebsocketApplication>(*args)
	val bithumb = ctx.getBean(BithumbService::class.java)
	val upbit = ctx.getBean(UpbitService::class.java)
	bithumb.getOrderBootDepth()
	upbit.getTickerDepth()
}
