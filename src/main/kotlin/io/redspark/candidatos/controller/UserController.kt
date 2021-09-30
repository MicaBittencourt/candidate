package io.redspark.candidatos.controller

import io.redspark.candidatos.models.dtos.UserListDTO.UserDTO
import io.redspark.candidatos.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/users")
class UserController(

        private val userService: UserService
) {

        @GetMapping("me")
        fun getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) @Valid email: String): UserDTO{
                return userService.getUser(email)
        }






}