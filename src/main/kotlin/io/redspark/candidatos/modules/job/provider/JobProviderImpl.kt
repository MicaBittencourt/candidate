package io.redspark.candidatos.modules.job.provider

import io.redspark.candidatos.database.entities.*
import io.redspark.candidatos.database.repositories.BusinessUnitRepository
import io.redspark.candidatos.database.repositories.CustomerRepository
import io.redspark.candidatos.database.repositories.JobRepository
import io.redspark.candidatos.database.repositories.JobTitleRepository
import io.redspark.candidatos.models.dtos.JobDTO
import io.redspark.candidatos.models.enums.JobStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class JobProviderImpl(
    private val jobRepository: JobRepository,
    private val jobTitleRepository: JobTitleRepository,
    private val customerRepository: CustomerRepository,
    private val businessUnitRepository: BusinessUnitRepository
) : JobProvider {

    override fun findAllJob(pageable: Pageable): Page<Job> = jobRepository.findAll(pageable)

    override fun findJobById(id: Long): Job? = jobRepository.findByIdOrNull(id)

    override fun findCustomerById(id: Long): Customer? = customerRepository.findByIdOrNull(id)

    override fun findBusinessUnitById(id: Long): BusinessUnit? = businessUnitRepository.findByIdOrNull(id)

    override fun findJobTitleById(id: Long): JobTitle? = jobTitleRepository.findByIdOrNull(id)

    override fun saveJob(job: Job): Job = jobRepository.save(job)

    override fun existsJobById(id: Long): Boolean = jobRepository.existsById(id)

    override fun updateJobEndDate(id: Long, endDate: LocalDateTime, status: JobStatus) = jobRepository.updateEndDate(id, endDate, status)

    override fun findAllByStatus(status: JobStatus, pageable: Pageable): Page<Job> = jobRepository.findByStatus(status, pageable)
    override fun findAll(searchJobSpecification: Specification<Job>, pageable: Pageable): Page<Job> = jobRepository.findAll(searchJobSpecification, pageable)

}