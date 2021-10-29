package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.candidatos.database.entities.Job
import io.redspark.candidatos.models.enums.JobStatus
import io.redspark.candidatos.models.enums.SlaStatus
import java.time.LocalDateTime

data class JobDTO(
    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("businessUnit")
    val businessUnitDTO: BusinessUnitDTO,

    @JsonProperty("requestingArea")
    val requestingArea: String,

    @JsonProperty("employeeName")
    val employeeName: String,

    @JsonProperty("slaInDays")
    val slaInDays: Int,

    @JsonProperty("customer")
    val customerDTO: CustomerDTO,

    @JsonProperty("jobTitle")
    val jobTitleDTO: JobTitleDTO,

    @JsonProperty("jobDescription")
    val jobDescription: String,

    @JsonProperty("candidateProfile")
    val candidateProfile: String,

    @JsonProperty("createdAt")
    val createdAt: LocalDateTime,

    @JsonProperty("finishedAt")
    val finishedAt: LocalDateTime?,

    @JsonProperty("status")
    val status: JobStatus,

    @JsonProperty("slaStatus")
    val slaStatus: SlaStatus
){

    constructor(job: Job, slaStatus: SlaStatus) : this (
        id = job.id,
        businessUnitDTO = BusinessUnitDTO(job.businessUnit),
        requestingArea = job.requestingArea,
        employeeName = job.employeeName,
        slaInDays = job.slaInDays,
        customerDTO = CustomerDTO(job.customer),
        jobTitleDTO = JobTitleDTO(job.jobTitle),
        jobDescription = job.jobDescription,
        candidateProfile = job.candidateProfile,
        createdAt = job.createdDate,
        finishedAt = job.endDate,
        status = job.status,
        slaStatus = slaStatus
    )

}
