package io.redspark.candidatos.database.entities

import io.redspark.candidatos.models.dtos.JobCreateDTO
import io.redspark.candidatos.models.enums.JobStatus
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "job")
class Job(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "bu_id")
    val businessUnit: BusinessUnit,

    @Column(name = "requesting_area")
    val requestingArea: String,

    @Column(name = "employee_name")
    val employeeName: String,

    @Column(name = "sla_in_days")
    val slaInDays: Int,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer,

    @ManyToOne
    @JoinColumn(name = "job_title_id")
    val jobTitle: JobTitle,

    @Column(name = "job_description")
    val jobDescription: String,

    @Column(name = "candidate_profile")
    val candidateProfile: String,

    @Column(name = "end_date")
    val endDate: LocalDateTime?,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: JobStatus,
) {

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime

    constructor(jobCreateDTO: JobCreateDTO, businessUnit: BusinessUnit, customer: Customer, jobTitle: JobTitle) : this (
        id = null,
        businessUnit = businessUnit,
        requestingArea = jobCreateDTO.requestingArea,
        employeeName = jobCreateDTO.employeeName,
        slaInDays = jobCreateDTO.slaInDays,
        customer = customer,
        jobTitle = jobTitle,
        jobDescription = jobCreateDTO.jobDescription,
        candidateProfile = jobCreateDTO.candidateProfile,
        endDate = null,
        status = JobStatus.OPENED
    )

}