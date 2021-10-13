package io.redspark.candidatos.modules.job.provider

import io.redspark.candidatos.database.entities.BusinessUnit
import io.redspark.candidatos.database.entities.Customer
import io.redspark.candidatos.database.entities.Job
import io.redspark.candidatos.database.entities.JobTitle
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

interface JobProvider {

    fun findAllJob(pageable: Pageable): Page<Job>
    fun findJobById(id: Long): Job?
    fun findCustomerById(id: Long): Customer?
    fun findBusinessUnitById(id: Long): BusinessUnit?
    fun findJobTitleById(id: Long): JobTitle?
    fun saveJob(job: Job): Job
    fun existsJobById(id: Long): Boolean
    fun updateJobEndDate(id: Long, endDate: LocalDateTime)

}