package com.example.webstorebackend.product.mapper

import org.springframework.stereotype.Component
import com.example.webstorebackend.product.dto.UserResponseDTO
import com.example.webstorebackend.product.dto.UserRequestDTO
import com.example.webstorebackend.product.entity.User

//not in use for now
@Component
object UserMapper {

    fun toUserEntity(dto: UserRequestDTO): User = User(
        username = dto.username,
        password = dto.password,
        name = dto.name,
        admin = dto.admin,
    )

    fun toUserDto(user: User): UserResponseDTO = UserResponseDTO(
        id = user.id,
        username = user.username,
        name = user.name,
        admin = user.admin
    )
}