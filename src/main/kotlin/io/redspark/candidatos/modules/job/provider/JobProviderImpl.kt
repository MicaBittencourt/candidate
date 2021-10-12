package io.redspark.candidatos.modules.job.provider

import io.redspark.candidatos.database.entities.BusinessUnit
import io.redspark.candidatos.database.entities.Customer
import io.redspark.candidatos.database.entities.Job
import io.redspark.candidatos.database.entities.JobTitle
import io.redspark.candidatos.database.repositories.BusinessUnitRepository
import io.redspark.candidatos.database.repositories.CustomerRepository
import io.redspark.candidatos.database.repositories.JobRepository
import io.redspark.candidatos.database.repositories.JobTitleRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    override fun updateJobEndDate(id: Long, endDate: LocalDateTime) = jobRepository.updateEndDate(id, endDate)

}