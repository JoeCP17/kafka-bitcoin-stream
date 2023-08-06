package org.bitcoin.infrastructure.jpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class JpaApplication

fun main(args: Array<String>) {
	runApplication<JpaApplication>(*args)
}
