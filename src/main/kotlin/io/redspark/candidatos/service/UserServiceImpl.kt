package io.redspark.candidatos.service

import io.redspark.candidatos.models.dtos.UserListDTO.UserDTO
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(

): UserService {
    override fun getUser(email: String): UserDTO {
        /*val userDTO: UserDTO = UserDTO(email = email, admin = true)
        return userDTO*/

        if (email.contains(".admin")) {
            val userDTO: UserDTO = UserDTO(email = email, admin = true)
            return userDTO

        }
        else{ val userDTO: UserDTO = UserDTO(email = email, admin = false)
            return userDTO }



    }
}