package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class UpdateCandidateDTO (
    @JsonProperty("id")
    val id: UUID? = null,

    @JsonProperty("name")
    @field:NotEmpty
    @field:NotNull
    var name: String,

    @JsonProperty("email")
    @field: Email
    @field:NotEmpty
    var email: String,

    @JsonProperty("linkedin")
    var linkedin: String,

    @JsonProperty("curriculum")
    var curriculum: String,

    @JsonProperty("phone")
    var phone: String,

    @JsonProperty("source")
    var source: String,

    @JsonProperty("skillList")
    var skillList: List<Long>,

)
