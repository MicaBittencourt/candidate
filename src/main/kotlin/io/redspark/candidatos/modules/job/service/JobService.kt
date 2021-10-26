package io.redspark.candidatos.modules.job.service

import io.redspark.candidatos.models.dtos.JobCreateDTO
import io.redspark.candidatos.models.dtos.JobDTO
import io.redspark.candidatos.models.dtos.JobPageDTO
import io.redspark.candidatos.models.dtos.JobTitleDTO
import io.redspark.candidatos.models.enums.JobStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

interface JobService {

    fun getJobPage(pageable: Pageable): Page<JobPageDTO>
    fun getJob(id: Long): JobDTO
    fun createJob(jobCreateDTO: JobCreateDTO): JobDTO
    fun closeJob(id: Long)
    fun searchJob(term: String?, status: JobStatus?, pageable: Pageable): Page<JobDTO>

}