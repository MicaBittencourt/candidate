package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.candidatos.database.entities.Candidate
import java.util.*

data class CandidatePageDTO (
        @JsonProperty("id")
        val id: UUID? = null,

        @JsonProperty("candidateName")
        val candidateName: String,

        @JsonProperty("fieldOfAction ")
        val jobTitle: String,

        @JsonProperty("skillList")
        val skillList: List<SkillDTO>
){

        constructor(candidate: Candidate) : this (
                id = candidate.id,
                candidateName =candidate.name,
                jobTitle = candidate.jobTitle.name,
                skillList = candidate.skillList.map { SkillDTO(it) }
               )
}