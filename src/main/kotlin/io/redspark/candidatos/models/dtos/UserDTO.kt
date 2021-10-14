package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.catalina.User
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class UserDTO (


        @JsonProperty("email")
        @NotNull
        @NotEmpty
        val email: String,

        @JsonProperty("admin")
        val admin: Boolean
        )