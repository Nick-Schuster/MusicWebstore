package com.example.webstorebackend.product.controller

import com.example.webstorebackend.product.dto.ProductReviewResponseDTO
import com.example.webstorebackend.product.dto.ProductReviewRequestDTO
import com.example.webstorebackend.product.service.ProductReviewService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products/{productId}/reviews")
class ProductReviewController(
    private val productReviewService: ProductReviewService
) {

    @GetMapping
    fun getReviews(@PathVariable productId: Long): ResponseEntity<List<ProductReviewResponseDTO>> {
        val reviews = productReviewService.getReviewsForProduct(productId)
        return ResponseEntity.ok(reviews)
    }

    @PostMapping
    fun addReview(
        @PathVariable productId: Long,
        @Valid @RequestBody dto: ProductReviewRequestDTO
    ): ResponseEntity<ProductReviewResponseDTO> {
        val saved = productReviewService.addReview(productId, dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(saved)
    }
}
