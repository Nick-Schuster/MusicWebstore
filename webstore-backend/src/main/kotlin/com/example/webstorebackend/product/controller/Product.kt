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

@RestController
@RequestMapping("/api/products")
class Product(private val productService: ProductService) {

    @GetMapping
    fun getAllProducts(
        @PageableDefault(size = 10, sort = ["id"])
        pageable: Pageable
    ): ResponseEntity<Page<ProductResponseDTO>> {
        val result = productService.getAllProducts(pageable)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/all")
    fun getAllProductsUnpaged(): List<ProductResponseDTO> {
        return productService.getAllProductsUnpaged()
    }


    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductResponseDTO> {
        val product = productService.getProductById(id)
        return if (product != null)
            ResponseEntity.ok(product)
        else
            ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createProduct(@RequestBody request: ProductRequestDTO): ResponseEntity<ProductResponseDTO> {
        val saved = productService.createProduct(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(saved)
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody request: ProductRequestDTO
    ): ResponseEntity<ProductResponseDTO> {
        val updated = productService.updateProduct(id, request)
        return if (updated != null)
            ResponseEntity.ok(updated)
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

    @GetMapping("/search")
    fun searchProducts(@RequestParam name: String): List<ProductResponseDTO> {
        return productService.searchProductsUnpaged(name)
    }

}
