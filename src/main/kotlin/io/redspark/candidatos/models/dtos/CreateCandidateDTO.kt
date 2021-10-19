package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreateCandidateDTO (

    @JsonProperty("id")
    val id: UUID? = null,

    @JsonProperty("name")
    @field:NotEmpty
    @field:NotNull
    var name: String,

    @JsonProperty("email")
    @field: Email
    var email: String,

    @JsonProperty("linkedin")
    @field:NotEmpty
    @field:NotNull
    var linkedin: String,

    @JsonProperty("curriculum")
    @field:NotEmpty
    @field:NotNull
    var curriculum: String,

    @JsonProperty("phone")
    @field:NotEmpty
    @field:NotNull
    var phone: String,

    @JsonProperty("source")
    @field:NotEmpty
    @field:NotNull
    var source: String,

    @JsonProperty("skillList")
    @field:NotEmpty
    @field:NotNull
    var skillList: List<Long>,

)
