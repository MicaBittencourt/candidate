package io.redspark.candidatos.controller

import io.redspark.candidatos.models.dtos.UserListDTO.UserDTO
import io.redspark.candidatos.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid
import javax.validation.constraints.Email

@Validated
@RestController
@RequestMapping("/users")
class UserController(

        private val userService: UserService
) {

        @GetMapping("me")
        fun getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) @Email @Valid email: String): UserDTO?{


                val userDTO: UserDTO? = userService.getUser(email)
                if (userDTO?.email == ""){
                        throw ResponseStatusException(HttpStatus.NOT_FOUND,"email.not.empty")
                }
                return userDTO

        }








}