package io.redspark.candidatos.modules.job.service

import io.redspark.candidatos.config.logger.LoggerDelegate
import io.redspark.candidatos.config.logger.logCreated
import io.redspark.candidatos.config.logger.logUpdated
import io.redspark.candidatos.database.entities.Job
import io.redspark.candidatos.models.dtos.JobCreateDTO
import io.redspark.candidatos.models.dtos.JobDTO
import io.redspark.candidatos.models.dtos.JobPageDTO
import io.redspark.candidatos.models.enums.SlaStatus
import io.redspark.candidatos.models.errors.ServiceError
import io.redspark.candidatos.models.errors.ServiceException
import io.redspark.candidatos.modules.job.provider.JobProvider
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
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

        jobProvider.updateJobEndDate(id, LocalDateTime.now())
    }

    private fun getSlaStatus(job: Job): SlaStatus {
        // TODO Calcular SLA atraves das datas e quantidade de dias
        return SlaStatus.GREEN
    }

}