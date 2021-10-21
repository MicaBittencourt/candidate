package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.candidatos.database.entities.Candidate
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CandidateDTO (

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
    var skillList: List<SkillDTO>,

){
    constructor(candidate: Candidate, skillList: List<SkillDTO>): this(
        id = candidate.id,
        name = candidate.name,
        email = candidate.email,
        phone = candidate.phone,
        source = candidate.source,
        linkedin = candidate.linkedin,
        curriculum = candidate.curriculum,
        skillList = skillList,
    )

    constructor(createCandidateDTO: CreateCandidateDTO): this(
        id = createCandidateDTO.id,
        name = createCandidateDTO.name,
        email = createCandidateDTO.email,
        phone = createCandidateDTO.phone,
        source = createCandidateDTO.source,
        linkedin = createCandidateDTO.linkedin,
        curriculum = createCandidateDTO.curriculum,
        skillList = listOf<SkillDTO>(),
    )

    constructor(updateCandidateDTO: UpdateCandidateDTO): this(
        id = updateCandidateDTO.id,
        name = updateCandidateDTO.name,
        email = updateCandidateDTO.email,
        phone = updateCandidateDTO.phone,
        source = updateCandidateDTO.source,
        linkedin = updateCandidateDTO.linkedin,
        curriculum = updateCandidateDTO.curriculum,
        skillList = listOf<SkillDTO>(),
    )


}



