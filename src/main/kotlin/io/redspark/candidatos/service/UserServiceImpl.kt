package io.redspark.candidatos.service

import io.redspark.candidatos.models.dtos.UserListDTO.UserDTO
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.validation.constraints.Email


@Service
class UserServiceImpl(

): UserService {
    override fun getUser(email: String): UserDTO {


        if ( email == ""){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"email.not.empty")
        }

        if (email.contains(".admin")) {
            val userDTO: UserDTO = UserDTO(email = email, admin = true)
            return userDTO

        }
        else{ val userDTO: UserDTO = UserDTO(email = email, admin = false)
            return userDTO }


    }

}