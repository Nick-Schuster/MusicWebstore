package com.example.webstorebackend.product.controller

import com.example.webstorebackend.product.dto.ProductRequestDTO
import com.example.webstorebackend.product.dto.ProductResponseDTO
import com.example.webstorebackend.product.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping
    fun getAllProducts(
        @PageableDefault(size = 10, sort = ["id"])
        pageable: Pageable
    ): ResponseEntity<Page<ProductResponseDTO>> {
        val result = productService.getAllProducts(pageable)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/all")
    fun getAllProductsUnpaged(): ResponseEntity<List<ProductResponseDTO>> {
        val result = productService.getAllProductsUnpaged()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductResponseDTO> {
        val product = productService.getProductById(id)
        return ResponseEntity.ok(product)
    }

    @PostMapping
    fun createProduct(@Valid @RequestBody request: ProductRequestDTO): ResponseEntity<ProductResponseDTO> {
        val saved = productService.createProduct(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(saved)
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @Valid @RequestBody request: ProductRequestDTO
    ): ResponseEntity<ProductResponseDTO> {
        val updated = productService.updateProduct(id, request)
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    fun deleteAllProducts(): ResponseEntity<Void> {
        productService.deleteAllProducts()
        return ResponseEntity.noContent().build()
    }


    @GetMapping("/search")
    fun searchProducts(@RequestParam name: String): ResponseEntity<List<ProductResponseDTO>> {
        val results = productService.searchProductsUnpaged(name)
        return ResponseEntity.ok(results)
    }
}

