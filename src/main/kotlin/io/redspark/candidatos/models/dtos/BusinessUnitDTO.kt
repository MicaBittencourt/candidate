package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.candidatos.database.entities.BusinessUnit

data class BusinessUnitDTO(
    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("name")
    val name: String
) {

    constructor(businessUnit: BusinessUnit) : this (
        id = businessUnit.id,
        name = businessUnit.name
    )

}
