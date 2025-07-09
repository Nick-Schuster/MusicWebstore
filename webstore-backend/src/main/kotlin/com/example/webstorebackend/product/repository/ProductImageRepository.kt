package com.example.webstorebackend.product.repository

import com.example.webstorebackend.product.entity.ProductImage
import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageRepository : JpaRepository<ProductImage, Long>
