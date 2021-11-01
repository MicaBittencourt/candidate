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

    @JsonProperty("candidateName")
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

    @JsonProperty("telephone")
    @field:NotEmpty
    @field:NotNull
    var phone: String,

    @JsonProperty("source")
    @field:NotEmpty
    @field:NotNull
    var source: String,

    @JsonProperty("skill")
    @field:NotEmpty
    @field:NotNull
    var skillList: List<SkillDTO>,

    @JsonProperty("fieldOfAction ")
    val jobTitle: JobTitleDTO

){
    constructor(candidate: Candidate): this(
            id = candidate.id,
            name = candidate.name,
            email = candidate.email,
            phone = candidate.phone,
            source = candidate.source,
            linkedin = candidate.linkedin,
            curriculum = candidate.curriculum,
            skillList = candidate.skillList.map { SkillDTO(it) },
            jobTitle = JobTitleDTO(candidate.jobTitle)

    )


}



