package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.candidatos.database.entities.Job
import io.redspark.candidatos.database.entities.JobTitle
import io.redspark.candidatos.models.enums.JobStatus
import io.redspark.candidatos.models.enums.SlaStatus
import java.time.LocalDateTime

data class JobPageDTO(
    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("createdAt")
    val createdAt: LocalDateTime,

    @JsonProperty("businessUnitName")
    val businessUnitName: String,

    @JsonProperty("customerName")
    val customerName: String,

    @JsonProperty("jobTitle")
    val jobTitle: String,

    @JsonProperty("status")
    val status: JobStatus,

    @JsonProperty("slaStatus")
    val slaStatus: SlaStatus
) {

    constructor(job: Job, slaStatus: SlaStatus) : this (
        id = job.id,
        createdAt = job.createdDate,
        businessUnitName = job.businessUnit.name,
        customerName = job.customer.name,
        jobTitle = job.jobTitle.name,
        status = job.status,
        slaStatus = slaStatus
    )

}
