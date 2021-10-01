package io.redspark.candidatos.service

import io.redspark.candidatos.models.dtos.UserListDTO.UserDTO
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import java.util.*


@Service
class UserServiceImpl(

): UserService {
    override fun getUser(email: String): UserDTO {

        if ( email == ""){
            return UserDTO(email = "", admin = false)
        }



        if (email.contains(".admin")) {
            val userDTO: UserDTO = UserDTO(email = email, admin = true)
            return userDTO

        }
        else{ val userDTO: UserDTO = UserDTO(email = email, admin = false)
            return userDTO }


    }

   /* override fun getUserByToken(token: String): UserDTO {
        val userDTO: UserDTO? = repository.getUserByToken(token)
        if (userDTO == null){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"user.not.found")
        }
        return userDTO
    }*/
}