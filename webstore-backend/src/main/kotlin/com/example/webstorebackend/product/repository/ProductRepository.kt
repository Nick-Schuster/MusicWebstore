package com.example.webstorebackend.product.repository

import com.example.webstorebackend.product.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Repository
interface ProductRepository : JpaRepository<Product, Long> {

    // unpaginated search
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    fun searchByNameIgnoreCaseUnpaged(@Param("name") name: String): List<Product>

    // paginated search
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    fun searchByNameIgnoreCasePaged(@Param("name") name: String, pageable: Pageable): Page<Product>
}


