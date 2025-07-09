package com.example.webstorebackend.product.controller

import com.example.webstorebackend.product.dto.ProductRequestDTO
import com.example.webstorebackend.product.dto.ProductResponseDTO
import com.example.webstorebackend.product.mapper.ProductMapper
import com.example.webstorebackend.product.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class Product(private val productService: ProductService) {

    @GetMapping
    fun getAllProducts(): List<ProductResponseDTO> =
        productService.getAllProducts().map { ProductMapper.toProductDto(it) }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductResponseDTO> {
        val product = productService.getProductById(id)
        return if (product != null)
            ResponseEntity.ok(ProductMapper.toProductDto(product))
        else
            ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createProduct(@RequestBody request: ProductRequestDTO): ResponseEntity<ProductResponseDTO> {
        val saved = productService.createProduct(ProductMapper.toProductEntity(request))
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toProductDto(saved))
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody request: ProductRequestDTO
    ): ResponseEntity<ProductResponseDTO> {
        val updated = productService.updateProduct(id, ProductMapper.toProductEntity(request))
        return if (updated != null)
            ResponseEntity.ok(ProductMapper.toProductDto(updated))
        else
            ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        return if (productService.deleteProduct(id))
            ResponseEntity.noContent().build()
        else
            ResponseEntity.notFound().build()
    }
}
