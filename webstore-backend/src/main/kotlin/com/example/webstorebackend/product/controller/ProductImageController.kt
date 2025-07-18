package com.example.webstorebackend.product.controller

import com.example.webstorebackend.product.dto.ProductImageRequestDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.example.webstorebackend.product.dto.ProductImageResponseDTO
import com.example.webstorebackend.product.service.ProductImageService

/**
 * REST controller for managing product images:
 * list, add, and delete image entries by product.
 */
@RestController
@RequestMapping("/api/products/{productId}/images")
class ProductImageController(
    private val productImageService: ProductImageService
) {

    @GetMapping
    fun getAllImages(@PathVariable productId: Long): List<ProductImageResponseDTO> =
        productImageService.getAllImages(productId)

    @PostMapping
    fun addImage(
        @PathVariable productId: Long,
        @RequestBody dto: ProductImageRequestDTO
    ): ResponseEntity<ProductImageResponseDTO> {
        val saved = productImageService.addImage(productId, dto)
        return ResponseEntity.ok(saved)
    }

    @DeleteMapping("/{imageId}")
    fun deleteImage(
        @PathVariable productId: Long,
        @PathVariable imageId: Long
    ): ResponseEntity<Void> {
        productImageService.deleteImage(productId, imageId)
        return ResponseEntity.noContent().build()
    }


}

