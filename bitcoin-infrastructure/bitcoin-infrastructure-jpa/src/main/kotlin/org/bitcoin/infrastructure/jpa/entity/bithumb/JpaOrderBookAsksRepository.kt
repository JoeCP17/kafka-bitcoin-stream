package org.bitcoin.infrastructure.jpa.entity.bithumb

import org.springframework.data.jpa.repository.JpaRepository

interface JpaOrderBookAsksRepository: JpaRepository<JpaOrderBookAsks, Long> {
}