package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.candidatos.database.entities.Candidate
import java.util.*

data class CandidateDTO (

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

//    @JsonProperty("stageList")
//    var stageList: List<StageDTO>

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

}



