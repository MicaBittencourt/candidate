package io.redspark.candidatos.modules.job.service

import au.com.console.jpaspecificationdsl.and
import io.redspark.candidatos.config.logger.LoggerDelegate
import io.redspark.candidatos.config.logger.logCreated
import io.redspark.candidatos.config.logger.logUpdated
import io.redspark.candidatos.database.entities.Job
import io.redspark.candidatos.database.specifications.JobSpecification
import io.redspark.candidatos.models.dtos.JobCreateDTO
import io.redspark.candidatos.models.dtos.JobDTO
import io.redspark.candidatos.models.dtos.JobPageDTO
import io.redspark.candidatos.models.enums.JobStatus
import io.redspark.candidatos.models.enums.SlaStatus
import io.redspark.candidatos.models.errors.ServiceError
import io.redspark.candidatos.models.errors.ServiceException
import io.redspark.candidatos.modules.job.provider.JobProvider
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class JobServiceImpl(
    private val jobProvider: JobProvider
) : JobService {

    private val logger by LoggerDelegate()

    override fun getJobPage(pageable: Pageable): Page<JobPageDTO> {
        val page = jobProvider.findAllJob(pageable)
        return page.map {
            val slaStatus = getSlaStatus(it)
            return@map JobPageDTO(it, slaStatus)
        }
    }

    override fun getJob(id: Long): JobDTO {
        val job = jobProvider.findJobById(id) ?: throw ServiceException(ServiceError.JOB_NOT_FOUND)
        val slaStatus = getSlaStatus(job)
        return JobDTO(job, slaStatus)
    }

    override fun createJob(jobCreateDTO: JobCreateDTO): JobDTO {
        val customer = jobProvider.findCustomerById(jobCreateDTO.customerId) ?: throw ServiceException(ServiceError.CUSTOMER_NOT_FOUND)
        val businessUnit = jobProvider.findBusinessUnitById(jobCreateDTO.businessUnitId) ?: throw ServiceException(ServiceError.BUSINESS_UNIT_NOT_FOUND)
        val jobTitle = jobProvider.findJobTitleById(jobCreateDTO.jobTitleId) ?: throw ServiceException(ServiceError.JOB_TITLE_NOT_FOUND)

        var job = Job(jobCreateDTO, businessUnit, customer, jobTitle)
        job = jobProvider.saveJob(job)

        logger.logCreated(job)

        return JobDTO(job, SlaStatus.GREEN)
    }

    @Transactional
    override fun closeJob(id: Long) {
        if (jobProvider.existsJobById(id).not()) {
            throw ServiceException(ServiceError.JOB_NOT_FOUND)
        }

        logger.logUpdated(Job::class.java, id, "job closed")

        jobProvider.updateJobEndDate(id, LocalDateTime.now(), JobStatus.CLOSED)
    }

    override fun searchJob(term: String?, status: JobStatus?, pageable: Pageable): Page<JobDTO> {
        val jobSpecification = JobSpecification()
        val searchJobSpecification = and(
            jobSpecification.searchJob(term),
            jobSpecification.filterStatus(status)
        )
        val pageJob = jobProvider.findAll(searchJobSpecification, pageable)

        return pageJob.map { JobDTO(it, getSlaStatus(it))}
    }

    private fun getSlaStatus(job: Job): SlaStatus {
        var current = LocalDateTime.now().toLocalDate()
        if (current.dayOfWeek == DayOfWeek.FRIDAY){
            current = current.plusDays(2)
        } else   if (current.dayOfWeek == DayOfWeek.SATURDAY){
            current = current.plusDays(1)
        }
        current = current.plusDays(1)
        val period = Duration.between(job.createdDate.toLocalDate().atStartOfDay(), current.atStartOfDay())
        val result = when (period.toDays()){
            in 0..10 -> SlaStatus.GREEN
            in 11..19 -> SlaStatus.YELLOW
            in 20.. 29 -> SlaStatus.ORANGE
            else -> SlaStatus.RED
        }
            return result
    }

}