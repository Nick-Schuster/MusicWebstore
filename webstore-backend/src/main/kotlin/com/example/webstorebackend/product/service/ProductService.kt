package com.example.webstorebackend.product.service

import com.example.webstorebackend.product.dto.ProductRequestDTO
import com.example.webstorebackend.product.dto.ProductResponseDTO
import com.example.webstorebackend.product.mapper.ProductMapper
import com.example.webstorebackend.product.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun getAllProducts(): List<ProductResponseDTO> =
        productRepository.findAll().map { ProductMapper.toProductDto(it) }

    fun getProductById(id: Long): ProductResponseDTO? {
        val product = productRepository.findById(id).orElse(null) ?: return null
        return ProductMapper.toProductDto(product)
    }

    fun createProduct(dto: ProductRequestDTO): ProductResponseDTO {
        val entity = ProductMapper.toProductEntity(dto)
        val saved = productRepository.save(entity)
        return ProductMapper.toProductDto(saved)
    }

    fun updateProduct(id: Long, updated: ProductRequestDTO): ProductResponseDTO? {
        val existing = productRepository.findById(id).orElse(null) ?: return null

        val updatedEntity = existing.copy(
            name = updated.name,
            description = updated.description,
            price = updated.price,
            inStock = updated.inStock
        )

        val saved = productRepository.save(updatedEntity)
        return ProductMapper.toProductDto(saved)
    }

    fun deleteProduct(id: Long): Boolean {
        return if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    fun searchProductsUnpaged(name: String): List<ProductResponseDTO> {
        return productRepository.searchByNameIgnoreCaseUnpaged(name)
            .map { ProductMapper.toProductDto(it) }
    }

    fun searchProductsPaged(name: String, pageable: Pageable): Page<ProductResponseDTO> {
        return productRepository.searchByNameIgnoreCasePaged(name, pageable)
            .map { ProductMapper.toProductDto(it) }
    }


    fun getAllProducts(pageable: Pageable): Page<ProductResponseDTO> {
        return productRepository.findAll(pageable)
            .map { ProductMapper.toProductDto(it) }
    }

    fun getAllProductsUnpaged(): List<ProductResponseDTO> {
        return productRepository.findAll()
            .map { ProductMapper.toProductDto(it) }
    }

}
