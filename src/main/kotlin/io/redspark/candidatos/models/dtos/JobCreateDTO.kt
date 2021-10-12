package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty

class JobCreateDTO(
    @JsonProperty("businessUnitId")
    val businessUnitId: Long,

    @JsonProperty("requestingArea")
    val requestingArea: String,

    @JsonProperty("employeeName")
    val employeeName: String,

    @JsonProperty("slaInDays")
    val slaInDays: Int,

    @JsonProperty("customerId")
    val customerId: Long,

    @JsonProperty("jobTitleId")
    val jobTitleId: Long,

    @JsonProperty("jobDescription")
    val jobDescription: String,

    @JsonProperty("candidateProfile")
    val candidateProfile: String
)
