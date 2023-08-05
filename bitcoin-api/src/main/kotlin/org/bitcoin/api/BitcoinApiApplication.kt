package org.bitcoin.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BitcoinApiApplication

fun main(args: Array<String>) {
	runApplication<BitcoinApiApplication>(*args)
}
