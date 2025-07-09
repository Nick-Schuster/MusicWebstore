package com.example.webstorebackend.product.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.example.webstorebackend.product.dto.ProductImageDTO
import com.example.webstorebackend.product.service.ProductImageService

@RestController
@RequestMapping("/api/products/{productId}/images")
class ProductImageController(
    private val productImageService: ProductImageService
) {

    @GetMapping
    fun getAllImages(@PathVariable productId: Long): List<ProductImageDTO> =
        productImageService.getAllImages(productId)

    @PostMapping
    fun addImage(
        @PathVariable productId: Long,
        @RequestBody dto: ProductImageDTO
    ): ResponseEntity<ProductImageDTO> {
        val saved = productImageService.addImage(productId, dto)
        return ResponseEntity.ok(saved)
    }

    @DeleteMapping("/{imageId}")
    fun deleteImage(
        @PathVariable productId: Long,
        @PathVariable imageId: Long
    ): ResponseEntity<Void> {
        return if (productImageService.deleteImage(productId, imageId)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}

