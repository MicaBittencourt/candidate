package io.redspark.candidatos.modules.user.service

import io.redspark.candidatos.models.dtos.UserDTO

interface UserService {
    fun getUser(email: String): UserDTO

}
