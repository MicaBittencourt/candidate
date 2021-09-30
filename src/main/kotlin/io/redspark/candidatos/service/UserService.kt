package io.redspark.candidatos.service

import io.redspark.candidatos.models.dtos.UserListDTO.UserDTO
import java.util.*

interface UserService {
    fun getUser(email: String): UserDTO

}
