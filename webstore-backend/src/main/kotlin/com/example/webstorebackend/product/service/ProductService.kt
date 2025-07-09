package com.example.webstorebackend.product.service

import com.example.webstorebackend.product.entity.Product
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: com.example.webstorebackend.product.repository.ProductRepository) {

    fun getAllProducts(): List<Product> =
        productRepository.findAll()

    fun getProductById(id: Long): Product? =
        productRepository.findById(id).orElse(null)

    fun createProduct(product: Product): Product =
        productRepository.save(product)

    fun updateProduct(id: Long, updatedProduct: Product): Product? {
        val existing = productRepository.findById(id).orElse(null) ?: return null

        val productToUpdate = existing.copy(
            name = updatedProduct.name,
            description = updatedProduct.description,
            price = updatedProduct.price,
            inStock = updatedProduct.inStock
        )

        return productRepository.save(productToUpdate)
    }

    fun deleteProduct(id: Long): Boolean {
        return if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}
