package org.bitcoin.infrastructure.jpa.bithumb.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "over_book")
class JpaOverBook(
    timestamp: String = "",
    paymentCurrency: String = "",
    orderCurrency: String = "",
    createdDate: LocalDateTime = LocalDateTime.now(),
    updatedDate: LocalDateTime = LocalDateTime.now()
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "over_book_id", nullable = false)
    var id: Long = 0
        protected set

    @Column(name = "time_stamp", length = 50, nullable = false)
    var timestamp: String = timestamp
        protected set

    @Column(name = "payment_currency", length = 50, nullable = false)
    var paymentCurrency: String = paymentCurrency
        protected set

    @Column(name = "order_currency", length = 50, nullable = false)
    var orderCurrency: String = orderCurrency
        protected set

    @Column(name = "created_date", nullable = false)
    var createdDate: LocalDateTime = createdDate
        protected set

    @Column(name = "updated_date", nullable = false)
    var updatedDate: LocalDateTime = updatedDate
        protected set
}