package com.example.webstorebackend.product.service

import com.example.webstorebackend.product.dto.UserRequestDTO
import com.example.webstorebackend.product.dto.UserResponseDTO
import com.example.webstorebackend.product.mapper.UserMapper
import com.example.webstorebackend.product.repository.UserRepository
import com.example.webstorebackend.common.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAllUsers(): List<UserResponseDTO> =
        userRepository.findAll().map(UserMapper::toUserDto)

    fun getUserById(id: Long): UserResponseDTO {
        val user = userRepository.findById(id)
            .orElseThrow { NotFoundException("User with id $id not found") }
        return UserMapper.toUserDto(user)
    }

    fun findByUsername(username: String): UserResponseDTO {
        val user = userRepository.findByUsername(username)
            ?: throw NotFoundException("User with username $username not found")
        return UserMapper.toUserDto(user)
    }

    fun createUser(dto: UserRequestDTO): UserResponseDTO {
        val entity = UserMapper.toUserEntity(dto)
        val saved = userRepository.save(entity)
        return UserMapper.toUserDto(saved)
    }

    fun deleteUser(id: Long): Boolean {
        if (!userRepository.existsById(id)) {
            throw NotFoundException("Cannot delete. User with id $id not found")
        }
        userRepository.deleteById(id)
        return true
    }

    fun deleteAllUsers() {
        userRepository.deleteAll()
    }
}
