package io.redspark.candidatos.modules.job.provider

import io.redspark.candidatos.database.entities.*
import io.redspark.candidatos.models.dtos.JobDTO
import io.redspark.candidatos.models.enums.JobStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime

interface JobProvider {

    fun findAllJob(pageable: Pageable): Page<Job>
    fun findJobById(id: Long): Job?
    fun findCustomerById(id: Long): Customer?
    fun findBusinessUnitById(id: Long): BusinessUnit?
    fun findJobTitleById(id: Long): JobTitle?
    fun saveJob(job: Job): Job
    fun existsJobById(id: Long): Boolean
    fun updateJobEndDate(id: Long, endDate: LocalDateTime, status: JobStatus)
    fun findAllByStatus(status: JobStatus, pageable: Pageable): Page<Job>
    fun findAll(searchJobSpecification: Specification<Job>, pageable: Pageable): Page<Job>

}