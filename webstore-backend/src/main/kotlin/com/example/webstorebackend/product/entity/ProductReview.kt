package com.example.webstorebackend.product.entity

import jakarta.persistence.*

@Entity
@Table(name = "product_review")
data class ProductReview(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val rating: Int,  // z. B. 1–5

    val comment: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product
)
