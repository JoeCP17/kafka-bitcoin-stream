package org.bitcoin.websocket

import org.bitcoin.websocket.bithumb.service.BithumbService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.bitcoin"])
class BitcoinWebsocketApplication

fun main(args: Array<String>) {
	val ctx = runApplication<BitcoinWebsocketApplication>(*args)
	val client = ctx.getBean(BithumbService::class.java)
	client.getOrderBootDepth()

}
