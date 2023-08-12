package org.bitcoin.infrastructure.jpa.bithumb.entity

import org.springframework.data.jpa.repository.JpaRepository

interface JpaOrderBookBidsRepository: JpaRepository<JpaOrderBookBids, Long> {
}