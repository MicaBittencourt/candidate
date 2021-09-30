package io.redspark.candidatos.models.dtos.UserListDTO

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.catalina.User
import java.util.*
import javax.validation.constraints.Email

class UserDTO (


        @JsonProperty("email")
        @field:Email
        val email: String,

        @JsonProperty("admin")
        val admin: Boolean
        )