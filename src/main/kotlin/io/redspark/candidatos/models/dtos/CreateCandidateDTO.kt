package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class CreateCandidateDTO (

    @JsonProperty("id")
    val id: UUID? = null,

    @JsonProperty("name")
    var name: String,

    @JsonProperty("email")
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
    var skillList: List<SkillDTO>,

    @JsonProperty("stageList")
    var stageList: List<StageDTO>

)
