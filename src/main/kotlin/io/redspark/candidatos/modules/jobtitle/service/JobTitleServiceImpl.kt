package io.redspark.candidatos.modules.jobtitle.service

import io.redspark.candidatos.config.logger.LoggerDelegate
import io.redspark.candidatos.config.logger.logCreated
import io.redspark.candidatos.config.logger.logUpdated
import io.redspark.candidatos.database.entities.JobTitle
import io.redspark.candidatos.database.repositories.JobTitleRepository
import io.redspark.candidatos.models.dtos.JobTitleDTO
import io.redspark.candidatos.models.errors.ServiceError
import io.redspark.candidatos.models.errors.ServiceException
import io.redspark.candidatos.modules.job.provider.JobProvider
import liquibase.pro.packaged.it
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class JobTitleServiceImpl(
    private val jobTitleRepository: JobTitleRepository
) : JobTitleService {

    private val logger by LoggerDelegate()

    override fun getJobTitleList(): List<JobTitleDTO> = jobTitleRepository.findAll()
        .map { JobTitleDTO(it) }

    override fun searchJobTitle(term: String?, pageable: Pageable): Page<JobTitleDTO> {
        val page = if (term == null) {
            jobTitleRepository.findAll(pageable)
        } else {
            jobTitleRepository.findAllByNameContainingIgnoreCase(term, pageable)
        }

        return page.map { JobTitleDTO(it) }
    }

    override fun getJobTitle(id: Long): JobTitleDTO {
        val jobTitle = jobTitleRepository.findByIdOrNull(id) ?: throw ServiceException(ServiceError.JOB_TITLE_NOT_FOUND)
        return JobTitleDTO(jobTitle)
    }

    override fun createJobTitle(jobTitleDTO: JobTitleDTO): JobTitleDTO {
        if (jobTitleDTO.id != null) {
            throw ServiceException(ServiceError.JOB_TITLE_ID_NOT_EMPTY)
        }

        var jobTitle = JobTitle(jobTitleDTO)
        jobTitle = jobTitleRepository.save(jobTitle)

        logger.logCreated(jobTitle)

        return JobTitleDTO(jobTitle)
    }

    override fun updateJobTitle(jobTitleDTO: JobTitleDTO): JobTitleDTO {
        if (jobTitleDTO.id == null || jobTitleRepository.existsById(jobTitleDTO.id).not()) {
            throw ServiceException(ServiceError.JOB_TITLE_NOT_FOUND)
        }

        var jobTitle = JobTitle(jobTitleDTO)
        jobTitle = jobTitleRepository.save(jobTitle)

        logger.logUpdated(jobTitle)

        return JobTitleDTO(jobTitle)
    }

}