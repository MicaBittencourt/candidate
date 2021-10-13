package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.candidatos.database.entities.BusinessUnit
import io.redspark.candidatos.database.entities.Customer

data class CustomerDTO(
    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("name")
    val name: String
) {

    constructor(customer: Customer) : this (
        id = customer.id,
        name = customer.name
    )

}
