package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class CreateSkillDTO (

    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("name")
    var name: String,


)
