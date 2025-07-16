package com.example.webstorebackend.product.controller

import com.example.webstorebackend.product.service.CurrentUserService
import com.example.webstorebackend.product.dto.UserResponseDTO
import com.example.webstorebackend.product.mapper.UserMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val CurrentUserService: CurrentUserService
) {

    @GetMapping("/me")
    fun getCurrentUser(): ResponseEntity<UserResponseDTO> {
        val user = CurrentUserService.getCurrentUser()
        return ResponseEntity.ok(UserMapper.toUserDto(user))
    }
}
