package com.example.webstorebackend.product.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.example.webstorebackend.product.entity.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}