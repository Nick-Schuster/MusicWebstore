package com.example.webstorebackend.product.service

import com.example.webstorebackend.common.auth.UserContextHolder
import com.example.webstorebackend.common.exception.NotFoundException
import com.example.webstorebackend.product.entity.User
import com.example.webstorebackend.product.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class CurrentUserService(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User {
        val userId = UserContextHolder.getUserId()
            ?: throw IllegalStateException("No user ID in request header (X-User-Id)")

        return userRepository.findById(userId)
            .orElseThrow { NotFoundException("User with ID $userId not found") }
    }
}
