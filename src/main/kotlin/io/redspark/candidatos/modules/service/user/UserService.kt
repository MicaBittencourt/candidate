package io.redspark.candidatos.modules.service.user

import io.redspark.candidatos.models.dtos.UserListDTO.UserDTO

interface UserService {
    fun getUser(email: String): UserDTO

}
