package com.example.webstorebackend.product.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    val name: String,

    val description: String? = null,

    @Column(nullable = false, precision = 10, scale = 2)
    val price: BigDecimal,

    @Column(name = "in_stock", nullable = false)
    val inStock: Boolean = true,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val images: List<ProductImage> = mutableListOf(),


    @Column(name = "average_rating", nullable = false)
    var averageRating: Double = 0.0,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val reviews: List<ProductReview> = mutableListOf()


)
