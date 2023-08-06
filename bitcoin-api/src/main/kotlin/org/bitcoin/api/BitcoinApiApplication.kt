package org.bitcoin.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.bitcoin"])
class BitcoinApiApplication

fun main(args: Array<String>) {
	runApplication<BitcoinApiApplication>(*args)
}
