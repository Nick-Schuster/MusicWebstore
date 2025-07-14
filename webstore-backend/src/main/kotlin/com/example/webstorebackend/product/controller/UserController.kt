package com.example.webstorebackend.product.controller

import com.example.webstorebackend.product.dto.UserRequestDTO
import com.example.webstorebackend.product.dto.UserResponseDTO
import com.example.webstorebackend.product.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponseDTO>> =
        ResponseEntity.ok(userService.getAllUsers())

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserResponseDTO> =
        ResponseEntity.ok(userService.getUserById(id))

    @GetMapping("/search")
    fun getUserByUsername(@RequestParam username: String): ResponseEntity<UserResponseDTO> =
        ResponseEntity.ok(userService.findByUsername(username))

    @PostMapping
    fun createUser(@RequestBody dto: UserRequestDTO): ResponseEntity<UserResponseDTO> =
        ResponseEntity.ok(userService.createUser(dto))

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Boolean> =
        ResponseEntity.ok(userService.deleteUser(id))

    @DeleteMapping("/all")
    fun deleteAllUsers(): ResponseEntity<Void> {
        userService.deleteAllUsers()
        return ResponseEntity.noContent().build()
    }
}
