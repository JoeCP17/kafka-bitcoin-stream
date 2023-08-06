package org.bitcoin.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication(scanBasePackages = ["org.bitcoin"])
class ProducerApplication

fun main(args: Array<String>) {
	runApplication<ProducerApplication>(*args)
}
