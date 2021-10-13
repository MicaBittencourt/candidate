package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.candidatos.database.entities.JobTitle

data class JobTitleDTO(
    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("name")
    val name: String
) {

    constructor(jobTitle: JobTitle) : this (
        id = jobTitle.id,
        name = jobTitle.name
    )

}
