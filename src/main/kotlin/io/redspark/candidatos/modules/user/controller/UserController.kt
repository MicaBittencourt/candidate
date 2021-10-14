package io.redspark.candidatos.modules.user.controller

import io.redspark.candidatos.models.dtos.UserDTO
import io.redspark.candidatos.modules.user.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

                return userService.getUser(email)

        }








}