package org.bitcoin.infrastructure.jpa.bithumb.entity

import org.springframework.data.jpa.repository.JpaRepository

interface JpaOverBookRepository: JpaRepository<JpaOverBook, Long> {

    fun findByOrderCurrency(symbol: String): JpaOverBook?
}