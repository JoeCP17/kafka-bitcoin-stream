package org.bitcoin.infrastructure.jpa.bithumb.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "order_book_bids")
class JpaOrderBookBids(
    quantity: String = "",
    price: String = "",
    overBookId: JpaOverBook? = JpaOverBook(),
    createdDate: LocalDateTime = LocalDateTime.now(),
    updatedDate: LocalDateTime = LocalDateTime.now()
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_book_bids_id", nullable = false)
    var id: Long = 0
        protected set

    @Column(name = "quantity", length = 30, nullable = false)
    var quantity: String = quantity
        protected set

    @Column(name = "price", length = 40, nullable = false)
    var price: String = price
        protected set

    @ManyToOne(targetEntity = JpaOverBook::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "over_book_id")
    var overBookId: JpaOverBook = overBookId!!
        protected set

    @Column(name = "created_date", nullable = false)
    var createdDate: LocalDateTime = createdDate
        protected set

    @Column(name = "updated_date", nullable = false)
    var updatedDate: LocalDateTime = updatedDate
        protected set
}